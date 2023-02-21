import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/deal")
public class Deal extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"C", "D", "H", "S"};

        ArrayList<String> deck = new ArrayList<>();
        ArrayList<String> playerHand = new ArrayList<>();
        ArrayList<String> dealerHand = new ArrayList<>();

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + suit);
            }
        }

        playerHand.add(deck.remove((int) (Math.random() * deck.size())));
        dealerHand.add(deck.remove((int) (Math.random() * deck.size())));
        playerHand.add(deck.remove((int) (Math.random() * deck.size())));
        dealerHand.add(deck.remove((int) (Math.random() * deck.size())));

        HttpSession session = request.getSession();

        session.setAttribute("deck", deck);
        session.setAttribute("playerHand", playerHand);
        session.setAttribute("dealerHand", dealerHand);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        out.print("{");
        out.print("\"deck\": [");
        for (String card : deck) {
            out.print("\"" + card + "\"");
            if (!card.equals(deck.get(deck.size() - 1))) {
                out.print(", ");
            }
        }
        out.print("], ");
        out.print("\"playerHand\": [");
        for (String card : playerHand) {
            out.print("\"" + card + "\"");
            if (!card.equals(playerHand.get(playerHand.size() - 1))) {
                out.print(", ");
            }
        }
        out.print("], ");
        out.print("\"dealerHand\": [");
        for (String card : dealerHand) {
            out.print("\"" + card + "\"");
            if (!card.equals(dealerHand.get(dealerHand.size() - 1))) {
                out.print(", ");
            }
        }
        out.println("]}");
        out.close();
    }
}
