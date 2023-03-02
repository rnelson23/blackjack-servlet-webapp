package com.example.blackjack;

import com.example.blackjack.models.Hand;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ViewServlet", value = "/")
public class ViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <title>Blackjack</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <h1>Blackjack</h1>");

        int money = 0;

        if (session != null) {
            int bet = (int) session.getAttribute("bet");
            money = (int) session.getAttribute("money");

            Hand playerHand = (Hand) session.getAttribute("playerHand");
            Hand dealerHand = (Hand) session.getAttribute("dealerHand");

            out.println("    <h2>Dealer: " + dealerHand.getDealerHandValue() + "</h2>");
            out.println("    <pre><code>" + dealerHand.toStringFancy() + "</code></pre>");
            out.println("    <h2>Player: " + playerHand.getHandValue() + "</h2>");
            out.println("    <pre><code>" + playerHand.toStringFancy() + "</code></pre>");

            if (playerHand.isBlackjack() && !dealerHand.isDealerBlackjack()) {
                out.println("    <p>Blackjack! Player wins!</p>");
                money += bet + (bet / 2);

            } else if (!playerHand.isBlackjack() && dealerHand.isDealerBlackjack()) {
                out.println("    <p>Blackjack! Dealer wins!</p>");
                dealerHand.flipLastCard();
                money -= bet;

            } else if (playerHand.isBlackjack() && dealerHand.isDealerBlackjack()) {
                out.println("    <p>Double Blackjack! Stand off!</p>");
                dealerHand.flipLastCard();

            } else if (playerHand.isBusted()) {
                out.println("    <p>Bust! Dealer wins!</p>");
                money -= bet;

            } else if (dealerHand.isBusted()) {
                out.println("    <p>Bust! Player wins!</p>");
                money += bet;

            } else if (playerHand.hasStood()) {

                if (playerHand.getHandValue() > dealerHand.getHandValue()) {
                    out.println("    <p>Player wins!</p>");
                    money += bet;

                } else if (playerHand.getHandValue() < dealerHand.getHandValue()) {
                    out.println("    <p>Dealer wins!</p>");
                    money -= bet;

                } else if (playerHand.getHandValue() == dealerHand.getHandValue()) {
                    out.println("    <p>Stand off!</p>");
                }

            } else {
                out.println("    <p>Money: " + (money < 0 ? "-" : "") + "$" + Math.abs(money) + "</p>");
                out.println("    <form action=\"/api\" method=\"get\">");
                out.println("        <input type=\"submit\" name=\"action\" value=\"hit\">");
                out.println("        <input type=\"submit\" name=\"action\" value=\"stand\">");
                out.println("    </form>");
                out.println("</body>");
                out.println("</html>");

                return;
            }

            session.setAttribute("money", money);
        }

        out.println("    <p>Money: " + (money < 0 ? "-" : "") + "$" + Math.abs(money) + "</p>");
        out.println("    <form action=\"/api\" method=\"get\">");
        out.println("        <input type=\"number\" name=\"bet\" placeholder=\"Bet\">");
        out.println("        <input type=\"submit\" name=\"action\" value=\"deal\">");
        out.println("    </form>");
        out.println("</body>");
        out.println("</html>");
    }
}
