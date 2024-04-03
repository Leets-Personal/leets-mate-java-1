package leets.leets_mate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LeetsMateApplicationTests {
	LeetsMateApplication app;

	@BeforeEach
	void setUp() {
		app = new LeetsMateApplication();
	}

	@Test
	void 입력받은_문자열을_파싱하여_리스트로_만든다() {
		String members = "a,b,c";

		List<String> actual = app.parseMembers(members);

		assertThat(actual).containsExactly("a", "b", "c");
	}

	@Test
	void 멤버수를_반환한다() {
		List<String> members = Arrays.asList("a", "b", "c");

		int actual = app.memberNumber(members);

		assertThat(actual).isEqualTo(3);
	}

	@Test
	void 멤버수와_최대_멤버수를_잘못_입력한_경우_예외를_반환한다() {
		assertThrows(Exception.class, () -> {
			app.checkDataValidity(3,4);
		});
	}

	@Test
	void 멤버_문자열에_영어를_입력한_경우_예외를_반환한다() {
		assertThrows(Exception.class, () -> {
			app.checkHasNoEnglish("haha");
		});
	}


	@Test
	void 랜덤_짝꿍_매칭을_하여_결과를_반환한다() {
		List<String> members = Arrays.asList("A", "B", "C", "D", "E", "F");
		int maximumGroupSize = 2;

		List<List<String>> expected = Arrays.asList(
				Arrays.asList("A", "B"),
				Arrays.asList("C", "D"),
				Arrays.asList("E", "F")
		);

		List<List<String>> actual = app.generateRandomGroups(members, maximumGroupSize);
		assertAll(
				() -> assertThat(actual.get(0)).containsExactly("A", "B"),
				() -> assertThat(actual.get(1)).containsExactly("C", "D"),
				() -> assertThat(actual.get(2)).containsExactly("E", "F")
		);
		assertThat(actual).isEqualTo(expected);
	}
}
