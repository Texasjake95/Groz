package groz;

import groz.entity.EnumMonsterType.EnumStatType;
import groz.entity.Player;
import groz.entity.attack.Attack;
import groz.zone.ZoneBase;

import java.util.Random;

/**
 * The main class of Groz
 * 
 * @author Texasjake95
 */
public class Groz {

	private static Player player;
	private static Random rand = new Random();

	public static void main(String[] args) {
		ZoneBase zone = null;
		Ref.initStatTypes();
		player = new Player("Zilx", 1);
		player.setLevel(1);
		player.getStats().addHP(5);
		player.getStats().setHealth(player.getStats().getMaxHealth());

		player.addAttack(new Attack(1d, .75d));
		player.addAttack(new Attack(1.5d, .5d));
		player.addAttack(new Attack(.75d, 1d));
		player.addAttack(new Attack(.8d, .8d).setGivesDamage(false));
		for (int i = 0; i < 10; i++) {
			zone = ZoneBase.randomZone.get(rand.nextInt(ZoneBase.randomZone
					.size()));
			Battle battle = new Battle(player, zone);
			player.getStats().setHealth(player.getStats().getMaxHealth());
			while (battle.canContinue()) {
				if (battle.monster.preformEffect(EnumStatType.SPD,
						battle.monster.getStats().getSpeed()) < player
						.preformEffect(EnumStatType.SPD, player.getStats()
								.getSpeed())) {
					if (!battle.monster.isDead() && !player.isDead()) {
						battle.Attack(player, 0, battle.monster);
					}
					if (!battle.monster.isDead() && !player.isDead()) {
						battle.Attack(battle.monster, 0, player);
					}
				} else {
					if (!battle.monster.isDead() && !player.isDead()) {
						battle.Attack(battle.monster, 0, player);
					}
					if (!battle.monster.isDead() && !player.isDead()) {
						battle.Attack(player, 0, battle.monster);
					}
				}
			}
			battle.monster.getStats().resetStats();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
		}
		System.out.println("Player health is " + player.getStats().getHealth());
	}
}
