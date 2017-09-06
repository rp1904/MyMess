package com.byb.bhojan;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/mvc-dispatcher-servlet.xml", "classpath:/spring-email.xml" })
@ComponentScan(basePackages = { "com.byb.bhojan" })
@WebAppConfiguration
public class ServiceTest {

	// @Autowired
	// private EmailService emailService;
	//
	// @Test
	// public void test() {
	//
	// appSessionServices.deleteAppSession("1f909b80-eadf-4488-be7d-0929e3296388");
	// System.out.println("--------------------------- "
	// +
	// appSessionServices.getUserIdPkByAPIkey("1eebc590-4c66-426e-864c-0e3966c27e25"));
	// }

	// @Test
	// public void test() {
	// emailService.sendEmail("roshanpatil1904@gmail.com", "Test", "I love you
	// !");
	// }
}
