package driftthespire.cards.skills;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import driftthespire.cards.BaseCard;
import driftthespire.cards.options.stepon.AccelerateOption;
import driftthespire.cards.options.stepon.SlowdownOption;
import driftthespire.character.TheAutomobile;
import driftthespire.util.CardInfo;

import java.util.ArrayList;

import static driftthespire.DriftTheSpire.makeID;

public class StepOn extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "StepOn", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            0, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            TheAutomobile.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 2;

    public StepOn() {
        super(cardInfo);
        setMagic(MAGIC, UPG_MAGIC);
        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> gearChoices = new ArrayList();
        gearChoices.add(new AccelerateOption());
        gearChoices.add(new SlowdownOption());
        addToBot(new ChooseOneAction(gearChoices));
    }
}
