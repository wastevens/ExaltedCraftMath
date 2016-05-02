package com.exalted.fight.attacks;

import com.exalted.fight.Fighter;
import com.exalted.fight.Stunt;

public class ClashAttack implements Attack {

	@Override
	public boolean attack(Fighter f1, Fighter f2) {
		Attack f1Attack = f1.attackAgainst(f2);
		Attack f2Attack = f2.attackAgainst(f1);
		Stunt f1Stunt = Stunt.stunt();
		Stunt f2Stunt = Stunt.stunt();
		int f1AttackSuccesses = f1Attack.attackSuccesses(f1, f1Stunt);
		int f2AttackSuccesses = f2Attack.attackSuccesses(f2, f2Stunt);
		String outcome = "Clash attack!\n";
		outcome += f1.name + " (" + f1Attack.name() + ") with " + f1Stunt.display() + " against " + f2.name + " (" + f2Attack.name() + ") with " + f2Stunt.display()+ "\n";
		if(f1AttackSuccesses == f2AttackSuccesses) {
			outcome += "Tie! (" + f1AttackSuccesses + " vs " + f2AttackSuccesses + ")";
			f1Attack.miss(f1);
			f2Attack.miss(f2);
			System.out.println(outcome);
			return false;
		}
		return (f1AttackSuccesses > f2AttackSuccesses) ? clash(f1, f2, f1Attack, f1AttackSuccesses, f2AttackSuccesses, outcome) : clash(f2, f1, f2Attack, f2AttackSuccesses, f1AttackSuccesses, outcome);
	}

	private boolean clash(Fighter winner, Fighter loser, Attack winningAttack, int winnerSuccesses, int loserSuccesses, String outcome) {
		outcome += winner.name + " strikes! (" + winnerSuccesses + " vs " + loserSuccesses + ")";
		return winningAttack.clashDamage(winner, loser, (winnerSuccesses - loserSuccesses), outcome);
	}
	
	@Override
	public int attackSuccesses(Fighter attacker, Stunt stunt) {
		throw new IllegalStateException("Should never be reached");
	}

	@Override
	public String name() {
		return "Clash attack";
	}

	@Override
	public void miss(Fighter f1) {
		throw new IllegalStateException("Should never be reached");
	}

	@Override
	public boolean clashDamage(Fighter attacker, Fighter defender, int threshold, String outcome) {
		throw new IllegalStateException("Should never be reached");
	}
}
