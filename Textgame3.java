package textgame1;

import java.util.Scanner;

public class Textgame3 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("**********RPG***********");
		Hero hero1 = new Hero();
		hero1.job_process();
		hero1.name_input();

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

	static void hunting_zone() {

	}

	static void potion_store() {

	}

}

class Hero {
	int hero_level, hero_power, hero_hp, hero_defense, hero_mp, hero_experience, hero_money, hero_killcount = 0,
			hero_deathcount = 0;
	String hero_job, hero_name;

	void job_process() {
		Scanner in = new Scanner(System.in);
		System.out.println("1. 전사\n2. 마법사\n3. 거인");
		hero_stat_init(in.nextInt());
		System.out.println(hero_job + "이 선택되었습니다.");
	}

	void hero_stat_init(int job_num) {
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
			hero_defense = 25;
			hero_hp = 80;
			hero_mp = 0;
			hero_experience = 0;
			hero_money = 0;
			hero_killcount = 0;
			hero_deathcount = 0;
		}
		case 3 -> {
			hero_job = "거인";
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
		}
	}

	void name_input() {
		Scanner in = new Scanner(System.in);
		System.out.println("영웅의 이름을 입력하세요. : ");
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

}

class Monster {

}

class PotionStore {

}