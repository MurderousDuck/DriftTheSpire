package driftthespire.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import driftthespire.DriftTheSpire;
import driftthespire.util.CardInfo;

import static driftthespire.DriftTheSpire.makeID;
import static driftthespire.util.TextureLoader.getCardTextureString;


public abstract class BaseCard extends CustomCard {
    protected CardStrings cardStrings;

    protected boolean upgradesDescription;

    protected int baseCost;

    protected boolean upgradeCost;
    protected boolean upgradeDamage;
    protected boolean upgradeBlock;
    protected boolean upgradeMagic;

    public int secondMagic;
    public int baseSecondMagic;
    public boolean upgradeSecondMagic;
    public boolean upgradedSecondMagic;
    public boolean isSecondMagicModified;

    public int speedGain;
    public int baseSpeedGain;
    public boolean upgradeSpeedGain;
    public boolean upgradedSpeedGain;
    public boolean isSpeedGainModified;

    public int speedLoss;
    public int baseSpeedLoss;
    public boolean upgradeSpeedLoss;
    public boolean upgradedSpeedLoss;
    public boolean isSpeedLossModified;

    protected int costUpgrade;
    protected int damageUpgrade;
    protected int blockUpgrade;
    protected int magicUpgrade;
    protected int secondMagicUpgrade;
    protected int speedGainUpgrade;
    protected int speedLossUpgrade;

    protected boolean baseExhaust = false;
    protected boolean upgExhaust = false;
    protected boolean baseEthereal = false;
    protected boolean upgEthereal = false;
    protected boolean baseInnate = false;
    protected boolean upgInnate = false;
    protected boolean baseRetain = false;
    protected boolean upgRetain = false;

    public BaseCard(CardInfo cardInfo) {
        this(cardInfo.baseId, cardInfo.baseCost, cardInfo.cardType, cardInfo.cardTarget, cardInfo.cardRarity, cardInfo.cardColor);
    }
    public BaseCard(CardInfo cardInfo, boolean upgradesDescription)
    {
        this(cardInfo.baseId, cardInfo.baseCost, cardInfo.cardType, cardInfo.cardTarget, cardInfo.cardRarity, cardInfo.cardColor, upgradesDescription);
    }

    public BaseCard(String baseID, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color)
    {
        super(makeID(baseID), "", getCardTextureString(baseID, cardType), cost, "", cardType, color, rarity, target);

        loadStrings();

        this.baseCost = cost;

        this.upgradesDescription = cardStrings.UPGRADE_DESCRIPTION != null;
        this.upgradeCost = false;
        this.upgradeDamage = false;
        this.upgradeBlock = false;
        this.upgradeMagic = false;

        this.costUpgrade = cost;
        this.damageUpgrade = 0;
        this.blockUpgrade = 0;
        this.magicUpgrade = 0;

        initializeTitle();
        initializeDescription();
    }

    public BaseCard(String cardName, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color, boolean upgradesDescription)
    {
        super(makeID(cardName), "", getCardTextureString(cardName, cardType), cost, "", cardType, color, rarity, target);

        loadStrings();

        this.baseCost = cost;

        this.upgradesDescription = upgradesDescription;
        this.upgradeCost = false;
        this.upgradeDamage = false;
        this.upgradeBlock = false;
        this.upgradeMagic = false;

        this.costUpgrade = cost;
        this.damageUpgrade = 0;
        this.blockUpgrade = 0;
        this.magicUpgrade = 0;

        initializeTitle();
        initializeDescription();
    }

    private void loadStrings() {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(cardID);

        this.rawDescription = cardStrings.DESCRIPTION;
        this.originalName = cardStrings.NAME;
        this.name = originalName;
    }

    //Methods meant for constructor use
    protected final void setDamage(int damage)
    {
        this.setDamage(damage, 0);
    }
    protected final void setBlock(int block)
    {
        this.setBlock(block, 0);
    }
    protected final void setMagic(int magic)
    {
        this.setMagic(magic, 0);
    }
    protected final void setSecondMagic(int secondMagic)
    {
        this.setSecondMagic(secondMagic, 0);
    }
    protected final void setSpeedGain(int speedGain)
    {
        this.setSpeedGain(speedGain, 0);
    }
    protected final void setSpeedLoss(int speedLoss)
    {
        this.setSpeedLoss(speedLoss, 0);
    }
    protected final void setCostUpgrade(int costUpgrade)
    {
        this.costUpgrade = costUpgrade;
        this.upgradeCost = true;
    }
    protected final void setExhaust(boolean exhaust) { this.setExhaust(exhaust, exhaust); }
    protected final void setEthereal(boolean ethereal) { this.setEthereal(ethereal, ethereal); }
    protected final void setInnate(boolean innate) {this.setInnate(innate, innate); }
    protected final void setSelfRetain(boolean retain) {this.setSelfRetain(retain, retain); }

    protected final void setDamage(int damage, int damageUpgrade)
    {
        this.baseDamage = this.damage = damage;
        if (damageUpgrade != 0)
        {
            this.upgradeDamage = true;
            this.damageUpgrade = damageUpgrade;
        }
    }
    protected final void setBlock(int block, int blockUpgrade)
    {
        this.baseBlock = this.block = block;
        if (blockUpgrade != 0)
        {
            this.upgradeBlock = true;
            this.blockUpgrade = blockUpgrade;
        }
    }
    protected final void setMagic(int magic, int magicUpgrade)
    {
        this.baseMagicNumber = this.magicNumber = magic;
        if (magicUpgrade != 0)
        {
            this.upgradeMagic = true;
            this.magicUpgrade = magicUpgrade;
        }
    }
    protected final void setSecondMagic(int secondMagic, int secondMagicUpgrade)
    {
        this.baseSecondMagic = this.secondMagic = secondMagic;
        if (secondMagicUpgrade != 0)
        {
            this.upgradeSecondMagic = true;
            this.secondMagicUpgrade = secondMagicUpgrade;
        }
    }
    protected final void setSpeedGain(int speedGain, int speedGainUpgrade)
    {
        this.baseSpeedGain = this.speedGain = speedGain;
        if (speedGainUpgrade != 0)
        {
            this.upgradeSpeedGain = true;
            this.speedGainUpgrade = speedGainUpgrade;
        }
    }
    protected final void setSpeedLoss(int speedLoss, int speedLossUpgrade)
    {
        this.baseSpeedLoss = this.speedLoss = speedLoss;
        if (speedLossUpgrade != 0)
        {
            this.upgradeSpeedLoss = true;
            this.speedLossUpgrade = speedLossUpgrade;
        }
    }
    protected final void setExhaust(boolean baseExhaust, boolean upgExhaust)
    {
        this.baseExhaust = baseExhaust;
        this.upgExhaust = upgExhaust;
        this.exhaust = baseExhaust;
    }
    protected final void setEthereal(boolean baseEthereal, boolean upgEthereal)
    {
        this.baseEthereal = baseEthereal;
        this.upgEthereal = upgEthereal;
        this.isEthereal = baseEthereal;
    }
    protected void setInnate(boolean baseInnate, boolean upgInnate)
    {
        this.baseInnate = baseInnate;
        this.upgInnate = upgInnate;
        this.isInnate = baseInnate;
    }
    protected void setSelfRetain(boolean baseRetain, boolean upgRetain)
    {
        this.baseRetain = baseRetain;
        this.upgRetain = upgRetain;
        this.selfRetain = baseRetain;
    }

    protected void upgradeSecondMagicNumber(int amount) {
        this.baseSecondMagic += amount;
        this.secondMagic = this.baseSecondMagic;
        this.upgradedSecondMagic = true;
    }

    protected void upgradeSpeedGain(int amount) {
        this.baseSpeedGain += amount;
        this.speedGain = this.baseSpeedGain;
        this.upgradedSpeedGain = true;
    }

    protected void upgradeSpeedLoss(int amount) {
        this.baseSpeedLoss += amount;
        this.speedLoss = this.baseSpeedLoss;
        this.upgradedSpeedLoss = true;
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();

        if (card instanceof BaseCard)
        {
            card.rawDescription = this.rawDescription;
            ((BaseCard) card).upgradesDescription = this.upgradesDescription;

            ((BaseCard) card).baseCost = this.baseCost;

            ((BaseCard) card).upgradeCost = this.upgradeCost;
            ((BaseCard) card).upgradeDamage = this.upgradeDamage;
            ((BaseCard) card).upgradeBlock = this.upgradeBlock;
            ((BaseCard) card).upgradeMagic = this.upgradeMagic;
            ((BaseCard) card).upgradeSecondMagic = this.upgradeSecondMagic;
            ((BaseCard) card).upgradeSpeedGain = this.upgradeSpeedGain;
            ((BaseCard) card).upgradeSpeedLoss = this.upgradeSpeedLoss;

            ((BaseCard) card).costUpgrade = this.costUpgrade;
            ((BaseCard) card).damageUpgrade = this.damageUpgrade;
            ((BaseCard) card).blockUpgrade = this.blockUpgrade;
            ((BaseCard) card).magicUpgrade = this.magicUpgrade;
            ((BaseCard) card).secondMagicUpgrade = this.secondMagicUpgrade;
            ((BaseCard) card).speedGainUpgrade = this.speedGainUpgrade;
            ((BaseCard) card).speedLossUpgrade = this.speedLossUpgrade;

            ((BaseCard) card).baseExhaust = this.baseExhaust;
            ((BaseCard) card).upgExhaust = this.upgExhaust;
            ((BaseCard) card).baseEthereal = this.baseEthereal;
            ((BaseCard) card).upgEthereal = this.upgEthereal;
            ((BaseCard) card).baseInnate = this.baseInnate;
            ((BaseCard) card).upgInnate = this.upgInnate;
            ((BaseCard) card).baseRetain = this.baseRetain;
            ((BaseCard) card).upgRetain = this.upgRetain;
        }

        return card;
    }

    @Override
    public void displayUpgrades() {
        super.displayUpgrades();

        if (this.upgradedSecondMagic) {
            this.secondMagic = this.baseSecondMagic;
            this.isSecondMagicModified = true;
        }

        if (this.upgradedSpeedGain) {
            this.speedGain = this.baseSpeedGain;
            this.isSpeedGainModified = true;
        }

        if (this.upgradedSpeedLoss) {
            this.speedLoss = this.baseSpeedLoss;
            this.isSpeedLossModified = true;
        }
    }

    @Override
    public void upgrade()
    {
        if (!upgraded)
        {
            this.upgradeName();

            if (this.upgradesDescription)
            {
                if (cardStrings.UPGRADE_DESCRIPTION == null)
                {
                    DriftTheSpire.logger.error("Card " + cardID + " upgrades description and has null upgrade description.");
                }
                else
                {
                    this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                }
            }

            if (upgradeCost)
            {
                if (isCostModified && this.cost < this.baseCost && this.cost >= 0) {
                    int diff = this.costUpgrade - this.baseCost; //how the upgrade alters cost
                    this.upgradeBaseCost(this.cost + diff);
                    if (this.cost < 0)
                        this.cost = 0;
                }
                else {
                    upgradeBaseCost(costUpgrade);
                }
            }

            if (upgradeDamage)
                this.upgradeDamage(damageUpgrade);

            if (upgradeBlock)
                this.upgradeBlock(blockUpgrade);

            if (upgradeMagic)
                this.upgradeMagicNumber(magicUpgrade);

            if (upgradeSecondMagic)
                this.upgradeSecondMagicNumber(secondMagicUpgrade);

            if (upgradeSpeedGain)
                this.upgradeSpeedGain(speedGainUpgrade);

            if (upgradeSpeedLoss)
                this.upgradeSpeedLoss(speedLossUpgrade);

            if (baseExhaust ^ upgExhaust)
                this.exhaust = upgExhaust;

            if (baseInnate ^ upgInnate)
                this.isInnate = upgInnate;

            if (baseEthereal ^ upgEthereal)
                this.isEthereal = upgEthereal;

            if (baseRetain ^ upgRetain)
                this.selfRetain = upgRetain;


            this.initializeDescription();
        }
    }
}