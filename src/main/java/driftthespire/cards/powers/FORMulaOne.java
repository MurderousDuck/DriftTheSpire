package driftthespire.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import driftthespire.cards.BaseCard;
import driftthespire.character.TheAutomobile;
import driftthespire.powers.FORMulaOnePower;
import driftthespire.powers.SpeedLimitPower;
import driftthespire.util.CardInfo;

import static driftthespire.DriftTheSpire.makeID;

public class FORMulaOne extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "FORMulaOne", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            3, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            TheAutomobile.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final int MAGIC_NUMBER = 100;
    private static final int UPG_MAGIC_NUMBER = 50;
    private static final int PERCENTAGE_NUMBER = 100;

    public FORMulaOne() {
        super(cardInfo);
        setMagic(MAGIC_NUMBER, UPG_MAGIC_NUMBER);
        setSecondMagic(PERCENTAGE_NUMBER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SpeedLimitPower(p, this.magicNumber), this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new FORMulaOnePower(p, 1), 1));
    }
}
