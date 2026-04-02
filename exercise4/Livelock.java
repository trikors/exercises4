
package exercise4;

class Spouse {
    private final String name;
    private boolean isHungry = true;

    public Spouse(String name) {
        this.name = name;
    }

    public void eat(Spoon spoon, Spouse otherSpouse) {
        while (isHungry) {
            if (spoon.getOwner() != this) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                continue;
            }

            if (otherSpouse.isHungry) {
                System.out.println(name + " you eat first!");
                spoon.setOwner(otherSpouse);
                continue;
            }

            spoon.use();
            isHungry = false;
            System.out.println(name + ": I am full!");
            spoon.setOwner(otherSpouse);
        }
    }
}

class Spoon {
    private Spouse owner;

    public Spoon(Spouse s) {
        owner = s;
    }

    public synchronized Spouse getOwner() {
        return owner;
    }

    public synchronized void setOwner(Spouse s) {
        owner = s;
    }

    public synchronized void use() {
        System.out.println("Eating...");
    }
}

public class Livelock {
    public static void main(String[] args) {
        final Spouse husband = new Spouse("Ivan");
        final Spouse wife = new Spouse("Natasha");
        final Spoon spoon = new Spoon(husband);

        Runnable task1 = () -> {
            husband.eat(spoon, wife);
        };
        Runnable task2 = () -> {
            wife.eat(spoon, husband);
        };

        new Thread(task1).start();
        new Thread(task2).start();
    }
}
