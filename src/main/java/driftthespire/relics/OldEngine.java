package driftthespire.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import driftthespire.character.TheAutomobile;
import driftthespire.powers.SpeedPower;

import static driftthespire.DriftTheSpire.makeID;

public class OldEngine extends BaseRelic {
    private static final String NAME = "OldEngine"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.HEAVY; //The sound played when the relic is clicked.
    private static final int SPEED_VALUE = 50;

    public OldEngine() {
        super(ID, NAME, TheAutomobile.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atPreBattle() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new SpeedPower(p, SPEED_VALUE), SPEED_VALUE, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0], SPEED_VALUE);
    }
}
