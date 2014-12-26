package com.lyx.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 测试配置同一组job，在一个线程中运行
 * 
 * @author Lenovo
 *
 */
@Component
public class AppMain3 {

	private static JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public static void main(String[] args)
			throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {

		long startTime = System.currentTimeMillis(); // 获取开始时间

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:spring-batch4.xml" });
		JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");

		int rowCount = jdbcTemplate.queryForObject(
				"select count(*) from people where "
						+ "first_name like '%JOHN%' or last_name like '%DOE%'",
				Integer.class);

		String sql1 = null;
		String sql2 = null;
		int mid = (rowCount - 1) >>> 1;
		if ((rowCount & 1) == 0) { // 偶数
			sql1 = "select first_name ,last_name from people where "
					+ "first_name like ? or last_name like ? limit 0," + mid;
			sql2 = "select first_name ,last_name from people where "
					+ "first_name like ? or last_name like ? order by person_id desc limit 0,"
					+ mid;
		} else { // 奇数
			sql1 = "select first_name ,last_name from people where "
					+ "first_name like ? or last_name like ? limit 0," + mid;
			sql2 = "select first_name ,last_name from people where "
					+ "first_name like ? or last_name like ? order by person_id desc limit 0,"
					+ (mid + 1);
		}

		JobParametersBuilder job1 = new JobParametersBuilder();
		job1.addString("sql1", sql1);
		Job task1 = (Job) context.getBean("addPeopleDescJob_1");

		JobExecution result1 = launcher.run(task1, job1.toJobParameters());
		ExitStatus es1 = result1.getExitStatus();
		if (es1.getExitCode().equals(ExitStatus.COMPLETED.getExitCode())) {
			System.out.println("job1任务正常完成");
		} else {
			System.out.println("job1任务失败，exitCode=" + es1.getExitCode());
		}

		JobParametersBuilder job2 = new JobParametersBuilder();
		// 设置JobParameter
		job2.addString("sql2", sql2);

		Job task2 = (Job) context.getBean("addPeopleDescJob_2");
		JobExecution result2 = launcher.run(task2, job2.toJobParameters());
		ExitStatus es2 = result2.getExitStatus();
		if (es2.getExitCode().equals(ExitStatus.COMPLETED.getExitCode())) {
			System.out.println("job2任务正常完成");
		} else {
			System.out.println("job2任务失败，exitCode=" + es2.getExitCode());
		}

		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
	}
}
