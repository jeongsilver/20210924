package test;

import java.util.Scanner;

public class PersonExe {
	private static PersonExe singleton = new PersonExe();
	private Person[] persons;

	private PersonExe() {
		persons = new Person[100];
		persons[0] = new Person("홍길동", Gender.MEN, "111-111");
		persons[1] = new Student("이정은", Gender.WOMEN, "222-222", "예담");
		persons[2] = new Student("김연경", Gender.WOMEN, "333-333", "예담");
		persons[3] = new Worker("김희진", Gender.MEN, "444-444", "삼성");
	}

	public static PersonExe getInstance() {
		return singleton;
	}

	public void execute() {
		while (true) {
			System.out.println("==================================================");
			System.out.println("1.친구추가 | 2.친구조회 | 3.친구수정 | 4.친구삭제 | 5. 종료 |");
			System.out.println("==================================================");
			int menu = ScanUtil.readInt("번호 선택: ");

			if (menu == 1) {
				System.out.println("✰✰✰✰✰");
				System.out.println("친구추가");
				System.out.println("✰✰✰✰✰");
				addFriend();
			} else if (menu == 2) {
				System.out.println("✰✰✰✰✰");
				System.out.println("친구조회");
				System.out.println("✰✰✰✰✰");
				showList();
			} else if (menu == 3) {
				System.out.println("✰✰✰✰✰");
				System.out.println("친구수정");
				System.out.println("✰✰✰✰✰");
				modify();
			} else if (menu == 4) {
				System.out.println("✰✰✰✰✰");
				System.out.println("친구삭제");
				System.out.println("✰✰✰✰✰");
				delete();
			} else if (menu == 5) {
				System.out.println("✰✰✰✰✰종료✰✰✰✰✰");
				break;
			}
		}
		System.out.println("정상적으로 종료합니다.");
	}

	private void addFriend() {
		System.out.println("1.동네친구 | 2.학교친구 | 3.회사동료");
		int choice = ScanUtil.readInt("번호 선택: ");

		Person person = null;
		String name = ScanUtil.readStr("친구 이름을 입력하세요");
		String phone = ScanUtil.readStr("연락처를 입력하세요");
		Gender gender = gender();

		if (choice == 1) {
			person = new Person(name, gender, phone);
//			System.out.println("성별: 1.남자 / 2.여자");
//			int gender = ScanUtil.readInt("번호 선택: ");
//			if (gender == 1) {
//				person = new Person(name, Gender.MEN, phone);
//			} else if (gender == 2) {
//				person = new Person(name, Gender.WOMEN, phone);
//			} else {
//				System.out.println("번호를 잘못 선택하셨습니다. 다시 입력해주세요.");
//			}
		} else if (choice == 2) {
			String school = ScanUtil.readStr("학교를 입력하세요");
			person = new Student(name, gender, phone, school);
		} else if (choice == 3) {

			String company = ScanUtil.readStr("회사이름 입력: ");
			person = new Student(name, gender, phone, company);

		}
		for (int i = 0; i < persons.length; i++) {
			if (persons[i] == null) {
				persons[i] = person;
				break;
			}
		}
		System.out.println("성공적으로 등록되었습니다.");
	}

	private void showList() {

		String name = ScanUtil.readStr("조회할 이름: ");
		int gender = ScanUtil.readInt("성별: 1.남자 / 2.여자");
		for (int i = 0; i < persons.length; i++) {
			if (persons[i] != null) {

				if (!name.equals("") && gender == 1) {
					if (persons[i].getName().indexOf(name) != -1 && persons[i].getGender().equals(Gender.MEN)) {
						System.out.println(persons[i].toString());
					} else if (!name.equals("") && gender == 2) {
						if (persons[i].getName().indexOf(name) != -1 && persons[i].getGender().equals(Gender.WOMEN)) {
							System.out.println(persons[i].toString());
						}

					} //
				} else if (!name.equals("")) {
					if (persons[i].getName().indexOf(name) != -1) {
						System.out.println(persons[i].toString());
					}
				} else if (gender == 1) {
					if (persons[i].getGender().equals(Gender.MEN)) {
						System.out.println(persons[i].toString());
					}
				} else if (gender == 2) {
					if (persons[i].getGender().equals(Gender.WOMEN)) {
						System.out.println(persons[i].toString());
					}
				} else if (gender == 0) {
					System.out.println("숫자 1 또는 2를 입력해주세요.");
					System.out.println("[친구목록]");
					System.out.println(persons[i].toString());
				}
			}
		}

	}

	private void modify() {
		for (int i = 0; i < persons.length; i++) {
			if (persons[i] != null)
				System.out.println("[" + i + "]" + persons[i].toString());
		}

		int num = ScanUtil.readInt("친구를 선택하세요");
		String phone = ScanUtil.readStr("바꿀 번호를 선택하세요");
		if (phone.equals("")) {
			persons[num].getPhone();
		} else {
			persons[num].setPhone(phone);
		}

		if (persons[num] instanceof Student) {
			String school = ScanUtil.readStr("바꿀 학교이름을 입력하세요");
			if (school.equals("")) {
				((Student) persons[num]).getSchool();
			} else
				((Student) persons[num]).setSchool(school);
		} else if (persons[num] instanceof Worker) {
			String company = ScanUtil.readStr("바꿀 회사이름을 입력하세요");
			if (company.equals("")) {
				((Worker) persons[num]).getCompany();
			} else
				((Worker) persons[num]).setCompany("바꿀 회사 이름을 입력하세요");
		}
		System.out.println("수정완료");
	}

	private void delete() {
		System.out.println("[친구목록]");
		for (int i = 0; i < persons.length; i++) {
			if (persons[i] != null) {
				System.out.println("[" + i + "]" + persons[i].toString());
			}
		}
		int num = ScanUtil.readInt("삭제할 번호를 입력하세요");
		if (persons[num] != null) {
			persons[num] = null;
			System.out.println("삭제 완료");
		} else if (persons[num] == null) {
			System.out.println("정보가 없습니다");
		}

	}

	public Gender gender() {
		String str = ScanUtil.readStr("성별: 1.남자 / 2.여자");
		if (str.equals("1")) {
			return Gender.MEN;
		} else if (str.equals("2")) {
			return Gender.WOMEN;
		} else if (str.equals("")) {
			System.out.println("잘못 입력하셨습니다.");
			return null;
		}
		return null;
	}

}
