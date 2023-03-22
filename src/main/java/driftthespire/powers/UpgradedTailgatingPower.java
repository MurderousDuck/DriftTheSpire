package driftthespire.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import driftthespire.cards.attacks.RearBump;

import static driftthespire.DriftTheSpire.makeID;

public class UpgradedTailgatingPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("UpgradedTailgatingPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;


    public UpgradedTailgatingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            AbstractCard rearBump = new RearBump();
            rearBump.upgrade();
            addToBot(new MakeTempCardInHandAction(rearBump, amount, false));
        }

    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new UpgradedTailgatingPower(owner, amount);
    }
}
