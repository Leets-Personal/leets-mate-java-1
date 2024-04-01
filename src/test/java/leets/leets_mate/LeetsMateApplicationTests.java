package leets.leets_mate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest
class LeetsMateApplicationTests {
	LeetsMateApplication app;

	@BeforeEach
	void setUp() {
		app = new LeetsMateApplication();
	}

	@Test
	void 입력받은_문자열을_파싱하여_리스트로_만든다() {
		List<String> expected = Arrays.asList("a", "b", "c");
		assertIterableEquals(expected, app.parseMembers("a,b,c"));
	}

	@Test
	void 멤버수를_반환한다() {
		List<String> expected = Arrays.asList("a", "b", "c");
		assertSame(3, app.memberNumber(expected));
	}

	@Test
	void 멤버수와_최대_멤버수를_잘못_입력한_경우_예외를_반환한다() {
		assertThrows(IllegalArgumentException.class, () -> {
			app.checkDataValidity(3,4);
		});
	}

	@Test
	void 멤버_문자열에_영어를_입력한_경우_예외를_반환한다() {
		assertThrows(IllegalArgumentException.class, () -> {
			app.checkHasNoEnglish("haha");
		});
	}


	@Test
	void 랜덤_짝꿍_매칭을_하여_결과를_반환한다() {
		List<String> memberList = Arrays.asList("A", "B", "C", "D", "E", "F");
		int maximumGroupSize = 2;

		List<List<String>> expected = Arrays.asList(
				Arrays.asList("A", "B"),
				Arrays.asList("C", "D"),
				Arrays.asList("E", "F")
		);

		List<List<String>> actual = app.generateRandomGroups(memberList, maximumGroupSize);
		assertIterableEquals(expected, actual);
	}
}
