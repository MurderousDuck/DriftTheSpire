package driftthespire.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static driftthespire.DriftTheSpire.makeID;
import static driftthespire.util.CharacterVariables.BASE_SPEED_INCREMENT;

public class GearPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("GearPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public GearPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new ApplyPowerAction(owner, owner, new SpeedPower(owner, amount * BASE_SPEED_INCREMENT), amount * BASE_SPEED_INCREMENT, AbstractGameAction.AttackEffect.NONE));
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new GearPower(owner, amount);
    }
}
