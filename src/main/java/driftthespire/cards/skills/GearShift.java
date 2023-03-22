package driftthespire.cards.skills;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import driftthespire.cards.BaseCard;
import driftthespire.cards.options.gearshift.*;
import driftthespire.character.CharacterUtils;
import driftthespire.character.TheAutomobile;
import driftthespire.util.CardInfo;

import java.util.ArrayList;

import static driftthespire.DriftTheSpire.makeID;

public class GearShift extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "GearShift", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            1, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.NONE, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            TheAutomobile.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);
    private float rotationTimer;
    private int previewIndex;
    private ArrayList<AbstractCard> cardsList = new ArrayList<>();

    public GearShift() {
        super(cardInfo);
        setCostUpgrade(0);
        cardsList.add(new ShiftUpOption());
        cardsList.add(new ShiftNeutralOption());
        cardsList.add(new ShiftDownOption());
        cardsList.add(new ShiftReverseOption());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> gearChoices = new ArrayList();

        //If already in the highest gear
        if(CharacterUtils.currentGear == 5)
            gearChoices.add(new LeaveItAloneOption());
        else
            gearChoices.add(new ShiftUpOption());

        //If already in neutral gear
        if(CharacterUtils.currentGear == 0)
            gearChoices.add(new LeaveItAloneOption());
        else
            gearChoices.add(new ShiftNeutralOption());

        //If already in neutral or reverse gear
        if(CharacterUtils.currentGear < 1)
            gearChoices.add(new LeaveItAloneOption());
        else
            gearChoices.add(new ShiftDownOption());

        //If already in reverse
        if(CharacterUtils.currentGear == -1)
            gearChoices.add(new LeaveItAloneOption());
        else
            gearChoices.add(new ShiftReverseOption());

        addToBot(new ChooseOneAction(gearChoices));
    }

    @Override
    public void update() {
        super.update();
        if (hb.hovered) {
            if (rotationTimer <= 0F) {
                rotationTimer = 2F;
                if (cardsList.size() == 0) {
                    cardsToPreview = CardLibrary.cards.get("Madness");
                } else {
                    cardsToPreview = cardsList.get(previewIndex);
                }
                if (previewIndex == cardsList.size() - 1) {
                    previewIndex = 0;
                } else {
                    previewIndex++;
                }
            } else {
                rotationTimer -= Gdx.graphics.getDeltaTime();
            }
        }
    }
}
