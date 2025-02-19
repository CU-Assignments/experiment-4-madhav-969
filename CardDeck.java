import java.util.*;

class Card {
    private String symbol;
    private String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " of " + symbol;
    }
}

public class CardDeck {
    private Collection<Card> deck;

    public CardDeck() {
        deck = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        String[] symbols = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        for (String symbol : symbols) {
            for (String value : values) {
                deck.add(new Card(symbol, value));
            }
        }
    }

    public Collection<Card> findCardsBySymbol(String symbol) {
        Collection<Card> result = new ArrayList<>();
        for (Card card : deck) {
            if (card.getSymbol().equalsIgnoreCase(symbol)) {
                result.add(card);
            }
        }
        return result;
    }

    public void displayAllCards() {
        for (Card card : deck) {
            System.out.println(card);
        }
    }

    public static void main(String[] args) {
        CardDeck cardDeck = new CardDeck();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCard Deck Menu:");
            System.out.println("1. Display all cards");
            System.out.println("2. Find cards by symbol");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nAll cards in the deck:");
                    cardDeck.displayAllCards();
                    break;
                case 2:
                    System.out.print("Enter the symbol (Hearts, Diamonds, Clubs, Spades): ");
                    String symbol = scanner.nextLine();
                    Collection<Card> foundCards = cardDeck.findCardsBySymbol(symbol);
                    if (foundCards.isEmpty()) {
                        System.out.println("No cards found for the given symbol.");
                    } else {
                        System.out.println("\nCards of " + symbol + ":");
                        for (Card card : foundCards) {
                            System.out.println(card);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
