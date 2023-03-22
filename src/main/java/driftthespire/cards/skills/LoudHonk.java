package driftthespire.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import driftthespire.cards.BaseCard;
import driftthespire.character.TheAutomobile;
import driftthespire.util.CardInfo;

import static driftthespire.DriftTheSpire.makeID;

public class LoudHonk extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "LoudHonk", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            0, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.ALL_ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            TheAutomobile.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final int MAGIC_NUMBER = 1;

    public LoudHonk() {
        super(cardInfo);
        setMagic(MAGIC_NUMBER);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.getCurrRoom().monsters.monsters
                .forEach(mon -> addToBot(new ApplyPowerAction(mon, p, new VulnerablePower(mon, magicNumber, false), magicNumber, true)));
        if(upgraded) {
            AbstractDungeon.getCurrRoom().monsters.monsters
                    .forEach(mon -> addToBot(new ApplyPowerAction(mon, p, new WeakPower(mon, magicNumber, false), magicNumber, true)));
        }
    }
}
