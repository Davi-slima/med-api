package med.voll.api;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
class ApiApplicationTests {

	@Test
	void contextLoads() {

		try(MockedStatic mock = mockStatic(SpringApplication.class)) {

			mock.when(() -> SpringApplication.run(ApiApplication.class, new String[]{}))
					.thenReturn(null);

			ApiApplication.main(new String[]{});

			mock.verify(() -> SpringApplication.run(ApiApplication.class, new String[]{}), times(1));
		}

	}

}
