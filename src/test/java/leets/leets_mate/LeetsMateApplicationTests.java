package leets.leets_mate;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class LeetsMateApplication {

	// 동작 함수입니다.
	public void run() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("[Leets 오늘의 짝에게]를 시작합니다.\n");
		System.out.println("멤버의 이름을 입력해 주세요. (,로 구분)");
		String members = scanner.nextLine();
		checkHasNoEnglish(members);

		System.out.println("\n최대 짝수를 입력해 주세요.");
		int maximumGroupSize = Integer.parseInt(scanner.nextLine());

		List<String> memberList = parseMembers(members);
		int memberCount = memberNumber(memberList);

		checkDataValidity(memberCount, maximumGroupSize);

		while (true) {
			List<List<String>> result = generateRandomGroups(memberList, maximumGroupSize);
			printResult(result);
			System.out.println("추천을 완료했습니다.");
			System.out.print("다시 구성하시겠습니까? (y or n):");
			String retry = scanner.nextLine();
			if (retry.equals("n")) {
				break;
			}
		}
		System.out.print("자리를 이동해 서로에게 인사해주세요.");
	}

	// 문자열로된 멤버들을 리스트로 분리하는 함수입니다.
	public List<String> parseMembers(String members) {
		return Arrays.asList(members.split(","));
	}

	// 총 멤버수를 반환합니다.
	public int memberNumber(List<String> members) {
		return members.size();
	}

	// 멤버 문자열에 영어가 있는지 검사합니다.
	public void checkHasNoEnglish(String members) {
		String regex = ".*[a-zA-Z].*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(members);
		if (matcher.matches()) {
			throw new IllegalArgumentException("잘못된 입력입니다.");
		}
	}
	// 멤버수와 최대 짝수 데이터가 유효한지 검사하는 함수입니다.
	public void checkDataValidity(int memberCount, int maximumGroupSize) {
		if (memberCount < maximumGroupSize) {
			throw new IllegalArgumentException("잘못된 입력입니다.");
		}
	}

	// 랜덤 짝꿍 추첨하는 함수 입니다.
	public List<List<String>> generateRandomGroups(List<String> memberList, int maximumGroupSize) {
		List<List<String>> groups = new ArrayList<>();
		for (int i = 0; i < memberList.size(); i += maximumGroupSize) {
			List<String> group = new ArrayList<>();
			for (int j = i; j < Math.min(i + maximumGroupSize, memberList.size()); j++) {
				group.add(memberList.get(j));
			}
			groups.add(group);
		}
		return groups;
	}

	// 결과를 프린트 하는 함수입니다.
	public void printResult(List<List<String>> result) {
		System.out.println(result);
	}

	public static void main(String[] args) {
		LeetsMateApplication app = new LeetsMateApplication();
		app.run();
	}
}