package driftthespire.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static driftthespire.DriftTheSpire.makeID;
import static driftthespire.character.CharacterUtils.doSpeedGain;
import static driftthespire.character.CharacterVariables.BASE_SPEED_INCREMENT;

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

    public void atStartOfTurnPostDraw() {
        doSpeedGain(amount * BASE_SPEED_INCREMENT);
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new GearPower(owner, amount);
    }
}
