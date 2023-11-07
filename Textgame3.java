package textgame1;

import java.util.Scanner;

public class Textgame3 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("**********RPG***********");
		System.out.println("1. 전사");
		System.out.println("2. 마법사");
		System.out.println("3. 무에타이 챔피언 강동흔");
		System.out.print("\n직업의 번호를 입력하세요. : ");
		Hero h1 = new Hero(in.nextInt());
		h1.name_input();

		while (true) {
			System.out.print("\n1. 사냥터\n2. 포션 상점\n\n입장할 장소를 입력하세요. : ");
			int entrance = in.nextInt();
			if (entrance == 1) {
				HuntingZone.process(h1);
			} else if (entrance == 2) {
				PotionStore.process(h1);

			}
		}
	}
}

class Hero {
	int hero_level, hero_power, hero_hp, hero_defense, hero_mp, hero_experience, hero_money, hero_killcount = 0,
			hero_deathcount = 0;
	int hero_damage;
	String hero_job, hero_name;
	boolean fight_is_over;

	public Hero(int job_num) {
		// hero_stat_init(job_num);//hero_stat_init 메서드 -> 생성자로 축소하자.
		switch (job_num) {
		case 1 -> {
			hero_job = "전사";
			hero_level = 1;
			hero_power = 15;
			hero_defense = 25;
			hero_hp = 80;
			hero_mp = 0;
			hero_experience = 0;
			hero_money = 0;
			hero_killcount = 0;
			hero_deathcount = 0;
		}
		case 2 -> {
			hero_job = "마법사";
			hero_level = 1;
			hero_power = 15;
			hero_defense = 20;
			hero_hp = 70;
			hero_mp = 10;
			hero_experience = 0;
			hero_money = 0;
			hero_killcount = 0;
			hero_deathcount = 0;
		}
		case 3 -> {
			hero_job = "무에타이 챔피언 강동흔";
			hero_level = 1;
			hero_power = 30;
			hero_defense = 40;
			hero_hp = 100;
			hero_mp = 0;
			hero_experience = 0;
			hero_money = 0;
			hero_killcount = 0;
			hero_deathcount = 0;
		}
		}
		System.out.println(this.hero_job + "가(이) 선택되었습니다.");
	}

	void name_input() {
		Scanner in = new Scanner(System.in);
		System.out.print("\n영웅의 이름을 입력하세요. : ");
		hero_name = in.next();
		System.out.println("이름이 입력되었습니다.");
		this.info();
	}

	void info() {
		System.out.println("\n*************************");
		System.out.println("현재 Hero의 이름 : " + hero_name);
		System.out.printf("현재 %s의 직업 : %s\n", hero_name, hero_job);
		System.out.printf("현재 %s의 레벨 : %d\n", hero_name, hero_level);
		System.out.printf("현재 %s의 힘 : %d\n", hero_name, hero_power);
		System.out.printf("현재 %s의 방어력 : %d\n", hero_name, hero_defense);
		System.out.printf("현재 %s의 HP : %d\n", hero_name, hero_hp);
		System.out.printf("현재 %s의 MP : %d\n", hero_name, hero_mp);
		System.out.printf("현재 %s의 경험치 : %d\n", hero_name, hero_experience);
		System.out.printf("현재 %s의 돈 : %d\n", hero_name, hero_money);
		System.out.printf("현재 %s의 killcount : %d\n", hero_name, hero_killcount);
		System.out.printf("현재 %s의 deathcount : %d\n", hero_name, hero_deathcount);
		System.out.println("*************************");
	}

	void hero_levelup() {
		if (hero_experience >= hero_level * 80) {
			hero_experience -= hero_level * 80;
			hero_level += 1;
			System.out.println("\n" + hero_name + "의 레벨이 증가했습니다. 현재 레벨 : " + hero_level);
			hero_money += hero_level * 50;
			System.out.printf("레벨업 기념으로 돈이 %d원 증가했습니다. 현재 돈 : %d\n", hero_level * 50, hero_money);
		}
	}

	void hero_attack(Monster m) {
		Scanner in = new Scanner(System.in);
		System.out.println("\n" + hero_name + "의 공격입니다.");

		switch (hero_job) {
		case "전사" -> {
			System.out.println("1. 쓰러스트(기본 스킬)");
			System.out.println("2. 사지 절단 베기(레벨 2 스킬)");
			System.out.println("3. 패닉의 함성(레벨 3 스킬)"); // 상대의 방어력을 0으로 만듦.
			System.out.print("공격 번호를 입력하세요. : ");
			int attack_num = in.nextInt();
			System.out.println();

			switch (attack_num) {
			case 1 -> {
				System.out.println("후이차!!");
				m.monster_damage = hero_level * 10 + hero_power * 30;
				System.out.printf("\n데미지는 %d입니다.\n", m.monster_damage);
			}
			case 2 -> {
				if (hero_level >= 2) {
					System.out.println("흐얏!");
					m.monster_power *= 0.75; // 공격력 3/4배 됨.
					m.monster_damage = hero_level * 10 + hero_power * 30; // 기본 데미지
					System.out.printf("\n데미지는 %d입니다.\n", m.monster_damage);
				} else {
					System.out.printf("레벨이 낮아 스킬을 사용할 수 없습니다. (스킬 요구 레벨 : 2)(현재 %s의 레벨 : %d)\n", hero_name, hero_level);
					hero_attack(m); // 재귀하여 스킬 재선택
				}
			}
			case 3 -> {
				if (hero_level >= 3) {
					System.out.println(")))!!!FUS RO DAH!!!(((");
					m.monster_defense /= 2; // 방어력 1/2배 됨.
					System.out.printf("\n%s는 오줌을 지렸다...(%s의 현재 방어력 : %d)\n", m.monster_name, m.monster_name,
							m.monster_defense);
				} else {
					System.out.printf("레벨이 낮아 스킬을 사용할 수 없습니다. (스킬 요구 레벨 : 3)(현재 %s의 레벨 : %d)\n", hero_name, hero_level);
					hero_attack(m); // 재귀하여 스킬 재선택
				}
			} // 스킬3 case문
			}// 스킬 전체 switch문
		} // 전사 case문
		case "마법사" -> {
			System.out.println("1. 파이어 볼(기본 스킬)"); // 기본 공격
			System.out.println("2. 내동댕이 치기(레벨 2 스킬)"); // 기본 공격
			System.out.println("3. 우울하게 만들기(레벨 3 스킬)"); // 힘, 방어력 감소
			System.out.print("공격 번호를 입력하세요. : ");
			int attack_num = in.nextInt();
			System.out.println();
			switch (attack_num) {
			case 1 -> {
				System.out.println("fire!");
				m.monster_damage = hero_level * 10 + hero_power * 15 + hero_mp * 30;
				System.out.printf("\n데미지는 %d입니다.\n", m.monster_damage);
			}
			case 2 -> {
				if (hero_level >= 2) {
					System.out.println("휙!~ 데굴데굴... 쿵!! 휙!~ ))!푸왁!(( ");
					m.monster_damage = hero_level * 10 + hero_power * 15 + hero_mp * 100;
					System.out.printf("\n데미지는 %d입니다.\n", m.monster_damage);
				} else {
					System.out.printf("레벨이 낮아 스킬을 사용할 수 없습니다. (스킬 요구 레벨 : 2)(현재 %s의 레벨 : %d)\n", hero_name, hero_level);
					hero_attack(m); // 재귀하여 스킬 재선택
				}
			}
			case 3 -> {
				if (hero_level >= 3) {
					System.out.println("너 여자친구 있냐?");
					m.monster_defense /= 2;// 방어력 1/2배 됨.
					m.monster_power /= 2;// 힘 1/2배 됨.
					hero_hp -= 5; // 히어로 또한 여자친구가 없기 때문에, 히어로도 데미지를 받는다.
					System.out.printf("\n 뚝..뚝.. %s가 눈물을 흘리기 시작했다.(%s의 현재 방어력 : %d)(%s의 현재 공격력 : %d)\n", m.monster_name,
							m.monster_name, m.monster_defense, m.monster_name, m.monster_power);
				} else {
					System.out.printf("레벨이 낮아 스킬을 사용할 수 없습니다. (스킬 요구 레벨 : 3)(현재 %s의 레벨 : %d)\n", hero_name, hero_level);
					hero_attack(m); // 재귀하여 스킬 재선택
				}
			} // 스킬3 case문
			}// 스킬 전체 switch문
		} // 마법사 case문
		case "무에타이 챔피언 강동흔" -> {
			System.out.println("1. 원-투-바디-로우(기본 스킬)"); // 기본 공격 : 무에타이 콤비네이션 공격
			System.out.println("2. 쁘아까오에게 전수받은 니킥(레벨 2 스킬)"); // 기본 공격
			System.out.println("3. 정찬성선수의 코리안 좀비모드(레벨 3 스킬)"); // 체력 증가
			System.out.println("4. A+을 위한 회심의 UPPERCUT(래밸 4 스킬)"); // 기본 공격
			System.out.print("공격 번호를 입력하세요. : ");
			int attack_num = in.nextInt();
			System.out.println();
			switch (attack_num) {
			case 1 -> {
				System.out.println("쉭! 쒹!! 퍽! 빡!!");
				m.monster_damage = hero_level * 10 + hero_power * 30;
				System.out.printf("\n데미지는 %d입니다.\n", m.monster_damage);
			}
			case 2 -> {
				if (hero_level >= 2) {
					System.out.println("하이약!!");
					m.monster_damage = hero_level * 10 + hero_power * 100;
					System.out.printf("\n데미지는 %d입니다.\n", m.monster_damage);
				} else {
					System.out.printf("레벨이 낮아 스킬을 사용할 수 없습니다. (스킬 요구 레벨 : 2)(현재 %s의 레벨 : %d)\n", hero_name, hero_level);
					hero_attack(m); // 재귀하여 스킬 재선택
				}
			}
			case 3 -> {
				if (hero_level >= 3) {
					System.out.println("zombie is IN YOUR HEAD");
					hero_hp += 100;
				} else {
					System.out.printf("레벨이 낮아 스킬을 사용할 수 없습니다. (스킬 요구 레벨 : 3)(현재 %s의 레벨 : %d)\n", hero_name, hero_level);
					hero_attack(m); // 재귀하여 스킬 재선택
				}
			} // 스킬3 case문
			case 4 -> {
				if (hero_level >= 4) {
					System.out.println("객지프는 최고의 과목이다!!!");
					System.out.println("UPPER!!!!\n...\n ..\n  .");
					System.out.println("triangular metrix!!!!");
					m.monster_damage = hero_level * 10 + hero_power * 9999999;
					System.out.printf("\n데미지는 %d입니다.\n", m.monster_damage);
				} else {
					System.out.printf("레벨이 낮아 스킬을 사용할 수 없습니다. (스킬 요구 레벨 : 4)(현재 %s의 레벨 : %d)\n", hero_name, hero_level);
					hero_attack(m); // 재귀하여 스킬 재선택
				}
			} // 스킬 전체 switch문
			}// 무에타이 챔피언 강동흔 case문
		} // h.hero_job switch문
		}// hero_attack 메소드

	}

	void hero_attacked() {
		if (hero_damage > hero_defense) {
			hero_hp = hero_hp + hero_defense - hero_damage;
		}
		if (hero_hp <= 0) {
			System.out.println(hero_name + "이(가) 죽었습니다.");
			fight_is_over = true; // 여기의 fight_is_over는 Hero 클래스의 필드임.
			hero_deathcount += 1;
			hero_hp = 1;
			System.out.println(hero_name + "이(가) 부활했습니다.");
			info();
		}
	}
}

class Monster {
	int monster_hp, monster_defense, monster_power, monster_mp, monster_experience, monster_level, monster_money;
	String monster_name;
	int monster_damage;
	boolean fight_is_over; // 모든 클래스를 아우르는 전역변수는 못 만들기 때문에, Hero와 Monster에 각각 하나씩 만들어야 함.

	public Monster(int monster_num) {
		// 원래의 monster_stat_init() 메서드 대신 생성자로 몬스터의 능력치를 초기화.
		switch (monster_num) {
		case 1 -> {
			monster_name = "족보만 공부하고 A+받은 대학생";
			monster_hp = 100;
			monster_mp = 0;
			monster_level = 1;
			monster_power = 20;
			monster_defense = 5;
			monster_money = 10;
			monster_experience = 10;
		}
		case 2 -> {
			monster_name = "고등학교 일진";
			monster_hp = 300;
			monster_mp = 0;
			monster_level = 5;
			monster_power = 100;
			monster_defense = 30;
			monster_money = 500;
			monster_experience = 50;
		}
		case 3 -> {
			monster_name = "가정파괴범";
			monster_hp = 500;
			monster_mp = 0;
			monster_level = 15;
			monster_power = 500;
			monster_defense = 250;
			monster_money = 20;
			monster_experience = 100;
		}
		case 4 -> {
			monster_name = "블라디미르 푸틴";
			monster_hp = 1000;
			monster_mp = 0;
			monster_level = 50;
			monster_power = 1000;
			monster_defense = 500;
			monster_money = 1000;
			monster_experience = 1000;
		}
		}
	}

	void monster_attack(Hero h) {
		System.out.println("\n" + monster_name + "의 공격입니다.");
		h.hero_damage = monster_power;
		System.out.printf("데미지는 %d입니다.\n", h.hero_damage);
	}

	void monster_attacked(Hero h) {
		if (monster_damage > monster_defense) {
			monster_hp = monster_hp + monster_defense - monster_damage;
		}
		if (monster_hp <= 0) {
			System.out.println("\n" + monster_name + "이(가) 죽었습니다.");
			h.hero_experience += monster_experience;
			h.hero_money += monster_money;
			fight_is_over = true; // 몬스터와의 싸움이 끝났기 때문에 break;를 통해 main문에 있는 무한 싸움을 빠져나오려고 했으나, 불가능하여 fight_is_over
									// 불린타입 변수를 통해 반복문 탈출
			h.hero_killcount += 1;
			h.hero_levelup();
			h.info();
		}
	}
}

class HuntingZone {
	// 사냥터를 여러 종류로 추가하고, 병렬적인 수행을 해야한다면, process 메서드를 인스턴스 메서드로 바꿔야 함.
	static void process(Hero h) {
		// process 메서드의 중요한 점은, 이 메서드를 통해 다른 클래스의 객체를 이 클래스 내부로 호출할 수 있다는 것이다.
		// 사냥터 프로세스가 시작하고나서 몬스터 객체를 생성한다.
		System.out.println("\n사냥터에 입장하였습니다.\n");
		System.out.println("1. 족보만 공부하고 A+받은 대학생");
		System.out.println("2. 고등학교 일진");
		System.out.println("3. 가정파괴범");
		System.out.println("4. 블라디미르 푸틴");
		System.out.print("\n전투할 상대를 입력하세요. : ");

		Scanner in = new Scanner(System.in);
		Monster m1 = new Monster(in.nextInt());

		System.out.println("\n" + m1.monster_name + "와의 전투를 시작합니다.");
		h.fight_is_over = false;
		m1.fight_is_over = false;
		while (true) {
			h.hero_attack(m1);
			m1.monster_attacked(h);
			if (h.fight_is_over || m1.fight_is_over) {
				break;
			}
			m1.monster_attack(h);
			h.hero_attacked();
			if (h.fight_is_over || m1.fight_is_over) {
				break;
			}
		}
	}
}

class PotionStore {
	static void process(Hero h) {
		Scanner in = new Scanner(System.in);
		System.out.println("\n포션 상점에 입장하였습니다.\n");
		System.out.println("1. 힘 증강 포션(30원)");
		System.out.println("2. 방어력 증강 포션(30원)");
		System.out.println("3. 경험치 증강 포션(100원)");
		System.out.println("4. HP 증강 포션(10원)");
		System.out.println("5. MP 증강 포션(10원)");
		System.out.print("\n원하시는 물건을 입력하세요. : ");
		int potion_num = in.nextInt();
		purchase(potion_num, h);

	}

	static void purchase(int potion_num, Hero h) {
		// 다음과 같이 클래스 안에서 외부 클래스의 객체를 호출할 수 있다.
		System.out.println("\n포션 상점에서 물건을 구매 시도하는 중입니다.");
		switch (potion_num) {
		case 1: // 힘 증강 포션 (30원)
			if (h.hero_money < 30) {
				System.out.println("잔액이 부족합니다. 나중에 재방문해주세요.");
			} else {
				h.hero_money -= 30;
				h.hero_power += 3;
				System.out.println("구입이 완료되었습니다.");
				System.out.println(h.hero_money + "원 남았습니다.");
			}
			break;
		case 2: // 방어력 증강 포션 (30원)
			if (h.hero_money < 30) {
				System.out.println("잔액이 부족합니다. 나중에 재방문해주세요.");
			} else {
				h.hero_money -= 30;
				h.hero_defense += 3;
				System.out.println("구입이 완료되었습니다.");
				System.out.println(h.hero_money + "원 남았습니다.");
			}
			break;
		case 3: // 경험치 증강 포션 (100원)
			if (h.hero_money < 100) {
				System.out.println("잔액이 부족합니다. 나중에 재방문해주세요.");
			} else {
				h.hero_money -= 100;
				h.hero_experience += 50;
				System.out.println("구입이 완료되었습니다.");
				System.out.println(h.hero_money + "원 남았습니다.");
				h.hero_levelup();
			}
			break;
		case 4: // HP 증강 포션 (10원)
			if (h.hero_money < 10) {
				System.out.println("잔액이 부족합니다. 나중에 재방문해주세요.");
			} else {
				h.hero_money -= 10;
				h.hero_hp += 50;
				System.out.println("구입이 완료되었습니다.");
				System.out.println(h.hero_money + "원 남았습니다.");
			}
			break;
		case 5: // MP 증강 포션 (10원)
			if (h.hero_money < 10) {
				System.out.println("잔액이 부족합니다. 나중에 재방문해주세요.");
			} else {
				h.hero_money -= 10;
				h.hero_mp += 50;
				System.out.println("구입이 완료되었습니다.");
				System.out.println(h.hero_money + "원 남았습니다.");
			}
			break;
		default:
			System.out.println("잘못된 입력입니다. 재방문해주세요.");
		}
		h.info();
	}
}
