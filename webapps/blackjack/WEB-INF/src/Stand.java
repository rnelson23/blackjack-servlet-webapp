import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/stand")
public class Stand extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ArrayList<String> deck = (ArrayList<String>) session.getAttribute("deck");
        ArrayList<String> playerHand = (ArrayList<String>) session.getAttribute("playerHand");
        ArrayList<String> dealerHand = (ArrayList<String>) session.getAttribute("dealerHand");

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
