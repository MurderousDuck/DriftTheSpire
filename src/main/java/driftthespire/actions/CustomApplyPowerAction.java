package driftthespire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.PowerBuffEffect;
import com.megacrit.cardcrawl.vfx.combat.PowerDebuffEffect;
import driftthespire.character.CharacterUtils;
import driftthespire.powers.FORMulaOnePower;
import driftthespire.powers.SpeedPower;

import java.util.Collections;
import java.util.Iterator;

public class CustomApplyPowerAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    public AbstractPower powerToApply;
    private float startingDuration;

    public CustomApplyPowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount, boolean isFast, AbstractGameAction.AttackEffect effect) {
        if (Settings.FAST_MODE) {
            this.startingDuration = 0.1F;
        } else if (isFast) {
            this.startingDuration = Settings.ACTION_DUR_FASTER;
        } else {
            this.startingDuration = Settings.ACTION_DUR_FAST;
        }

        this.setValues(target, source, stackAmount);
        this.duration = this.startingDuration;
        this.powerToApply = powerToApply;
        if (AbstractDungeon.player.hasPower(FORMulaOnePower.POWER_ID) && source != null && source.isPlayer && powerToApply.ID.equals(SpeedPower.POWER_ID)) {
            AbstractDungeon.player.getPower(FORMulaOnePower.POWER_ID).flash();
            powerToApply.amount = CharacterUtils.getSpeedAfterMultiplier(powerToApply.amount);
            amount = CharacterUtils.getSpeedAfterMultiplier(amount);
        }

        this.actionType = ActionType.POWER;
        this.attackEffect = effect;
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.duration = 0.0F;
            this.startingDuration = 0.0F;
            this.isDone = true;
        }

    }

    public CustomApplyPowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount, boolean isFast) {
        this(target, source, powerToApply, stackAmount, isFast, AttackEffect.NONE);
    }

    public CustomApplyPowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerToApply) {
        this(target, source, powerToApply, powerToApply.amount);
    }

    public CustomApplyPowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount) {
        this(target, source, powerToApply, stackAmount, false, AttackEffect.NONE);
    }

    public CustomApplyPowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount, AbstractGameAction.AttackEffect effect) {
        this(target, source, powerToApply, stackAmount, false, effect);
    }

    public void update() {
        if (this.target != null && !this.target.isDeadOrEscaped()) {
            if (this.duration == this.startingDuration) {
                if (this.source != null) {
                    Iterator var1 = this.source.powers.iterator();

                    while(var1.hasNext()) {
                        AbstractPower pow = (AbstractPower)var1.next();
                        pow.onApplyPower(this.powerToApply, this.target, this.source);
                    }
                }

                if (this.target instanceof AbstractMonster && this.target.isDeadOrEscaped()) {
                    this.duration = 0.0F;
                    this.isDone = true;
                    return;
                }

                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
                boolean hasBuffAlready = false;
                Iterator var6 = this.target.powers.iterator();

                label148:
                while(true) {
                    AbstractPower p;
                    do {
                        do {
                            if (!var6.hasNext()) {
                                if (this.powerToApply.type == PowerType.DEBUFF) {
                                    this.target.useFastShakeAnimation(0.5F);
                                }

                                if (!hasBuffAlready) {
                                    this.target.powers.add(this.powerToApply);
                                    Collections.sort(this.target.powers);
                                    this.powerToApply.onInitialApplication();
                                    this.powerToApply.flash();
                                    if (this.amount >= 0 || !this.powerToApply.ID.equals("Strength") && !this.powerToApply.ID.equals("Dexterity") && !this.powerToApply.ID.equals("Focus")) {
                                        if (this.powerToApply.type == PowerType.BUFF) {
                                            AbstractDungeon.effectList.add(new PowerBuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name));
                                        } else {
                                            AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name));
                                        }
                                    } else {
                                        AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
                                    }

                                    AbstractDungeon.onModifyPower();
                                    if (this.target.isPlayer) {
                                        int buffCount = 0;
                                        Iterator var8 = this.target.powers.iterator();

                                        while(var8.hasNext()) {
                                            AbstractPower abstractPower = (AbstractPower)var8.next();
                                            if (abstractPower.type == PowerType.BUFF) {
                                                ++buffCount;
                                            }
                                        }

                                        if (buffCount >= 10) {
                                            UnlockTracker.unlockAchievement("POWERFUL");
                                        }
                                    }
                                }
                                break label148;
                            }

                            p = (AbstractPower)var6.next();
                        } while(!p.ID.equals(this.powerToApply.ID));
                    } while(p.ID.equals("Night Terror"));

                    p.stackPower(this.amount);
                    p.flash();
                    if ((p instanceof StrengthPower || p instanceof DexterityPower) && this.amount <= 0) {
                        AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
                    } else if (this.amount > 0) {
                        if (p.type != PowerType.BUFF && !(p instanceof StrengthPower) && !(p instanceof DexterityPower)) {
                            AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, "+" + Integer.toString(this.amount) + " " + this.powerToApply.name));
                        } else {
                            AbstractDungeon.effectList.add(new PowerBuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, "+" + Integer.toString(this.amount) + " " + this.powerToApply.name));
                        }
                    } else if (p.type == PowerType.BUFF) {
                        AbstractDungeon.effectList.add(new PowerBuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
                    } else {
                        AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
                    }

                    p.updateDescription();
                    hasBuffAlready = true;
                    AbstractDungeon.onModifyPower();
                }
            }

            this.tickDuration();
        } else {
            this.isDone = true;
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ApplyPowerAction");
        TEXT = uiStrings.TEXT;
    }
}

