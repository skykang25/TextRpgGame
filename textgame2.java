package textgame1;

import java.util.Scanner;

public class textgame2 {
	static int hero_level, hero_power, hero_hp, hero_defense, hero_mp, hero_experience, hero_money, hero_killcount = 0,
			hero_deathcount = 0;
	static int monster_hp, monster_defense, monster_power, monster_mp, monster_experience, monster_level, monster_money;
	static String hero_name, monster_name;
	static int monster_damage, hero_damage;
	static boolean fight_is_over;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		name_input();
		while (true) {
			System.out.print("\n1. 사냥터\n2. 포션 상점\n입장할 장소를 입력하세요. : ");
			int entrance = in.nextInt();
			if (entrance == 1) {
				hunting_zone();
			} else if (entrance == 2) {
				potion_store();
			}
		}
	}

	static void name_input() {
		Scanner in = new Scanner(System.in);
		System.out.print("영웅의 이름을 입력하세요. : ");
		hero_name = in.next();
		System.out.println("이름이 입력되었습니다.");
		hero_stat_init(); // 히어로 능력치 초기화는 main 문에 다가 넣어도 됨.
		System.out.println("게임에 입장하였습니다.");
		info();
	}

	static void info() {
		System.out.println("\n*************************");
		System.out.println("현재 Hero의 이름 : " + hero_name);
		System.out.printf("현재 %s의 레벨 : %d\n", hero_name, hero_level);
		System.out.printf("현재 %s의 힘 : %d\n", hero_name, hero_power);
		System.out.printf("현재 %s의 방어력 : %d\n", hero_name, hero_defense);
		System.out.printf("현재 %s의 HP : %d\n", hero_name, hero_hp);
		System.out.printf("현재 %s의 MP : %d\n", hero_name, hero_mp);
		System.out.printf("현재 %s의 경험치 : %d\n", hero_name, hero_experience);
		System.out.printf("현재 %s의 돈 : %d\n", hero_name, hero_money);
		System.out.println("*************************");
	}

	static void hunting_zone() {
		Scanner in = new Scanner(System.in);
		System.out.println("\n사냥터에 입장하였습니다.");
		System.out.println("1. 너구리\n2. 살쾡이");
		System.out.print("전투할 상대를 입력하세요. : ");
		int monster_num = in.nextInt();
		monster_stat_init(monster_num);
		System.out.println("\n" + monster_name + "와의 전투를 시작합니다.");
		fight_is_over = false;
		while (true) {
			hero_attack();
			monster_attacked();
			if (fight_is_over) {
				break;
			}
			monster_attack();
			hero_attacked();
			if (fight_is_over) {
				break;
			}
		}

	}

	static void hero_stat_init() {
		hero_level = 1;
		hero_power = 15;
		hero_defense = 25;
		hero_hp = 80;
		hero_mp = 0;
		hero_experience = 0;
		hero_money = 0;
		hero_killcount = 0;
	}

	static void monster_stat_init(int num) {
		if (num == 1) {
			monster_name = "너구리";
			monster_hp = 100;
			monster_mp = 0;
			monster_level = 1;
			monster_power = 20;
			monster_defense = 5;
			monster_money = 10;
			monster_experience = 10;
		} else if (num == 2) {
			monster_name = "살쾡이";
			monster_hp = 2000;
			monster_mp = 0;
			monster_level = 5;
			monster_power = 100;
			monster_defense = 20;
			monster_money = 30;
			monster_experience = 50;
		}
	}

	static void potion_store() {
		Scanner in = new Scanner(System.in);
		System.out.println("\n포션 상점에 입장하였습니다.");
		System.out.println("1. 힘 증강 포션(30원)");
		System.out.println("2. 방어력 증강 포션(30원)");
		System.out.println("3. 경험치 증강 포션(100원)");
		System.out.println("4. HP 증강 포션(10원)");
		System.out.println("5. MP 증강 포션(10원)");
		System.out.print("원하시는 물건을 입력하세요. : ");
		int potion_num = in.nextInt();
		// potionStore_show(hero_money, potion_num);
		System.out.println("\n포션 상점에서 물건을 구매 시도하는 중입니다.");
		switch (potion_num) {
		case 1: // 힘 증강 포션 (30원)
			if (hero_money < 30) {
				System.out.println("잔액이 부족합니다. 나중에 재방문해주세요.");
			} else {
				hero_money -= 30;
				hero_power += 3;
				System.out.println("구입이 완료되었습니다.");
				System.out.println(hero_money + "원 남았습니다.");
			}
			break;
		case 2: // 방어력 증강 포션 (30원)
			if (hero_money < 30) {
				System.out.println("잔액이 부족합니다. 나중에 재방문해주세요.");
			} else {
				hero_money -= 30;
				hero_defense += 3;
				System.out.println("구입이 완료되었습니다.");
				System.out.println(hero_money + "원 남았습니다.");
			}
			break;
		case 3: // 경험치 증강 포션 (100원)
			if (hero_money < 100) {
				System.out.println("잔액이 부족합니다. 나중에 재방문해주세요.");
			} else {
				hero_money -= 100;
				hero_experience += 50;
				System.out.println("구입이 완료되었습니다.");
				System.out.println(hero_money + "원 남았습니다.");
				hero_levelup();
			}
			break;
		case 4: // HP 증강 포션 (10원)
			if (hero_money < 10) {
				System.out.println("잔액이 부족합니다. 나중에 재방문해주세요.");
			} else {
				hero_money -= 10;
				hero_hp += 50;
				System.out.println("구입이 완료되었습니다.");
				System.out.println(hero_money + "원 남았습니다.");
			}
			break;
		case 5: // MP 증강 포션 (10원)
			if (hero_money < 10) {
				System.out.println("잔액이 부족합니다. 나중에 재방문해주세요.");
			} else {
				hero_money -= 10;
				hero_mp += 50;
				System.out.println("구입이 완료되었습니다.");
				System.out.println(hero_money + "원 남았습니다.");
			}
			break;
		default:
			System.out.println("잘못된 입력입니다. 재방문해주세요.");
		}
		info();

	}

	static void hero_attack() {
		Scanner in = new Scanner(System.in);
		System.out.println("\n" + hero_name + "의 공격입니다.");
		System.out.println("1. 쓰러스트");
		System.out.print("공격 번호를 입력하세요. : ");
		int attack_num = in.nextInt();
		if (attack_num == 1) {
			monster_damage = hero_level * 10 + hero_power * 30;
			System.out.printf("\n데미지는 %d입니다.\n", monster_damage);
		}
	}

	static void monster_attacked() {
		if (monster_damage > monster_defense) {
			monster_hp = monster_hp + monster_defense - monster_damage;
		}
		if (monster_hp <= 0) {
			System.out.println(monster_name + "가 죽었습니다.");
			hero_experience += monster_experience;
			hero_money += monster_money;
			fight_is_over = true; // 몬스터와의 싸움이 끝났기 때문에 break;를 통해 main문에 있는 무한 싸움을 빠져나오려고 했으나, 불가능하여 fight_is_over
									// 불린타입 변수를 통해 반복문 탈출
			hero_killcount += 1;
			hero_levelup();
			info();
		}
	}

	static void monster_attack() {
		System.out.println("\n" + monster_name + "의 공격입니다.");
		hero_damage = monster_power;
		System.out.printf("데미지는 %d입니다.\n", hero_damage);
	}

	static void hero_attacked() {
		if (hero_damage > hero_defense) {
			hero_hp = hero_hp + hero_defense - hero_damage;
		}
		if (hero_hp <= 0) {
			System.out.println(hero_name + "가 죽었습니다.");
			fight_is_over = true;
			hero_deathcount += 1;
			hero_hp = 1;
			System.out.println(hero_name + "가 부활했습니다.");
			info();
		}
	}

	static void hero_levelup() {
		if (hero_experience >= hero_level * 80) {
			hero_experience -= hero_level * 80;
			hero_level += 1;
			System.out.println("\n" + hero_name + "의 레벨이 증가했습니다. 현재 레벨 : " + hero_level);
			hero_money += hero_level * 50;
			System.out.printf("레벨업 기념으로 돈이 %d원 증가했습니다. 현재 돈 : %d\n", hero_level * 50, hero_money);
		}
	}

}
