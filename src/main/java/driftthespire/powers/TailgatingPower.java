package driftthespire.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import driftthespire.cards.attacks.RearBump;

import static driftthespire.DriftTheSpire.makeID;

public class TailgatingPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("TailgatingPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;


    public TailgatingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            addToBot(new MakeTempCardInHandAction(new RearBump(), amount, false));
        }

    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new TailgatingPower(owner, amount);
    }
}
