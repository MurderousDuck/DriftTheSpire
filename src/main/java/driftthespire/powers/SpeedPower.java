package driftthespire.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import driftthespire.util.CustomTags;

import static driftthespire.DriftTheSpire.makeID;
import static driftthespire.util.CharacterVariables.BASE_SPEED_INCREMENT;
import static driftthespire.util.CharacterVariables.BASE_SPEED_LIMIT;

public class SpeedPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("SpeedPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public SpeedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if(amount <= 0) {
            addToBot(new RemoveSpecificPowerAction(owner, owner, SpeedPower.POWER_ID));
            return;
        }

        int speedLimit = owner.getPower(SpeedLimitPower.POWER_ID) != null ? owner.getPower(SpeedLimitPower.POWER_ID).amount : 0;
        if (amount > (speedLimit > 0 ? speedLimit : BASE_SPEED_LIMIT)) {
            flash();
            addToBot(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix((int)(speedLimit / 4f), true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
            addToBot(new DamageAction(owner, new DamageInfo(owner, (int)(speedLimit / 20f), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
            addToBot(new RemoveSpecificPowerAction(owner, owner, SpeedPower.POWER_ID));
        }
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if(!card.tags.contains(CustomTags.DRIFT) && card.type == AbstractCard.CardType.ATTACK && this.amount > 0)
            this.amount -= BASE_SPEED_INCREMENT;
        this.updateDescription();
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * (1 + this.amount/100f) : damage;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new SpeedPower(owner, amount);
    }
}
