package driftthespire.util;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import driftthespire.powers.GearPower;
import driftthespire.powers.ReverseGearPower;
import driftthespire.powers.SpeedPower;

import static driftthespire.util.CharacterVariables.BASE_SPEED_INCREMENT;
import static driftthespire.util.CharacterVariables.EXTRA_SPEED_INCREMENT;

public class CharacterUtils {
    /* ACCELERATE */
    public static void accelerate(int amount) {
        AbstractPlayer p = AbstractDungeon.player;
        GameActionManager gam = AbstractDungeon.actionManager;
        gam.addToBottom(new ApplyPowerAction(p, p,
                new SpeedPower(p, amount * BASE_SPEED_INCREMENT + EXTRA_SPEED_INCREMENT), amount * BASE_SPEED_INCREMENT + EXTRA_SPEED_INCREMENT));
    }

    /* GEAR SHIFT */
    public static int currentGear = 0;

    public static void increaseGear() {
        AbstractPlayer p = AbstractDungeon.player;
        GameActionManager gam = AbstractDungeon.actionManager;
        if(currentGear == -1) {
            undoReverseGear(p, gam);
        }
        if(currentGear < 5) {
            gam.addToBottom(new ApplyPowerAction(p, p, new GearPower(p, 1), 1));
            gam.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, -1), -1));
            currentGear++;
        }
    }

    public static void decreaseGear() {
        AbstractPlayer p = AbstractDungeon.player;
        GameActionManager gam = AbstractDungeon.actionManager;
        if(currentGear == 1) {
            undoGear(p, gam);
            currentGear--;
        }
        if(currentGear > 1) {
            gam.addToBottom(new ApplyPowerAction(p, p, new GearPower(p, -1), -1));
            gam.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1));
            currentGear--;
        }
    }

    public static void neutral() {
        AbstractPlayer p = AbstractDungeon.player;
        GameActionManager gam = AbstractDungeon.actionManager;
        if(currentGear == -1) {
            undoReverseGear(p, gam);
        }
        if(currentGear > 0) {
            undoGear(p, gam);
        }
        currentGear = 0;
    }

    public static void reverse() {
        AbstractPlayer p = AbstractDungeon.player;
        GameActionManager gam = AbstractDungeon.actionManager;
        if(currentGear > 0) {
            undoGear(p, gam);
        }
        if (p.getPower(ReverseGearPower.POWER_ID) == null) {
            gam.addToBottom(new ApplyPowerAction(p, p, new ReverseGearPower(p)));
            gam.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, ReverseGearPower.REVERSE_GEAR_AMOUNT), ReverseGearPower.REVERSE_GEAR_AMOUNT));
        }
        currentGear = -1;
    }

    private static void undoReverseGear(AbstractPlayer p, GameActionManager gam) {
        gam.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, -ReverseGearPower.REVERSE_GEAR_AMOUNT), -ReverseGearPower.REVERSE_GEAR_AMOUNT));
        gam.addToBottom(new RemoveSpecificPowerAction(p, p, ReverseGearPower.POWER_ID));
    }

    private static void undoGear(AbstractPlayer p, GameActionManager gam) {
        AbstractPower power = p.getPower(GearPower.POWER_ID);
        gam.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, power.amount), power.amount));
        gam.addToBottom(new RemoveSpecificPowerAction(p, p, GearPower.POWER_ID));
    }
}
