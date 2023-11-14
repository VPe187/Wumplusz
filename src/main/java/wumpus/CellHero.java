package wumpus;

/**
 * CellHero class used to store one HERO field in Wumpus world.
 */
public class CellHero extends Cell {

    private int arrows;
    private HeroSight heroSight;

    public CellHero(String value, int arrows) {
        super(value);
        this.arrows = arrows;
    }

    public int getArrows() {
        return arrows;
    }

    public HeroSight getHeroSight() {
        return heroSight;
    }

    public void setHeroSight(HeroSight heroSight) {
        this.heroSight = heroSight;
    }

    /**
     * Hero shot its arrow.
     */
    public void shot() {
        if (this.arrows > 0) {
            this.arrows--;
        }
    }
}
