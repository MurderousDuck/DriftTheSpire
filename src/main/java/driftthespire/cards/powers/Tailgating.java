package driftthespire.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import driftthespire.cards.BaseCard;
import driftthespire.cards.attacks.RearBump;
import driftthespire.character.TheAutomobile;
import driftthespire.powers.TailgatingPower;
import driftthespire.powers.UpgradedTailgatingPower;
import driftthespire.util.CardInfo;

import static driftthespire.DriftTheSpire.makeID;

public class Tailgating extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Tailgating", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            1, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            TheAutomobile.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);

    public Tailgating() {
        super(cardInfo);
        cardsToPreview = new RearBump();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(upgraded) {
            addToBot(new ApplyPowerAction(p, p, new UpgradedTailgatingPower(p, 1), 1));
        } else {
            addToBot(new ApplyPowerAction(p, p, new TailgatingPower(p, 1), 1));
        }
    }
}
