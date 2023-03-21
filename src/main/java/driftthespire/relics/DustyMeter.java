package driftthespire.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import driftthespire.character.TheAutomobile;
import driftthespire.powers.SpeedLimitPower;

import static driftthespire.DriftTheSpire.makeID;
import static driftthespire.util.CharacterVariables.BASE_SPEED_LIMIT;

public class DustyMeter extends BaseRelic {
    private static final String NAME = "DustyMeter"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.SPECIAL; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public DustyMeter() {
        super(ID, NAME, TheAutomobile.Enums.CARD_COLOR, RARITY, SOUND);
    }


    @Override
    public void atPreBattle() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new SpeedLimitPower(p, BASE_SPEED_LIMIT), BASE_SPEED_LIMIT, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
