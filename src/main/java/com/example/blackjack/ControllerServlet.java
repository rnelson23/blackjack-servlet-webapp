package com.example.blackjack;

import com.example.blackjack.models.Deck;
import com.example.blackjack.models.Hand;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ControllerServlet", value = "/api")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        switch (action) {
            case "deal": {
                Deck deck = new Deck();
                deck.shuffle();

                Hand playerHand = new Hand();
                Hand dealerHand = new Hand();

                deck.dealFaceUp(playerHand);
                deck.dealFaceUp(dealerHand);
                deck.dealFaceUp(playerHand);
                deck.dealFaceDown(dealerHand);

                int bet = Integer.parseInt(request.getParameter("bet"));

                session.setAttribute("deck", deck);
                session.setAttribute("playerHand", playerHand);
                session.setAttribute("dealerHand", dealerHand);
                session.setAttribute("bet", bet);

                if (session.getAttribute("money") == null) {
                    session.setAttribute("money", 0);
                }

                break;
            }

            case "hit": {
                Deck deck = (Deck) session.getAttribute("deck");
                Hand playerHand = (Hand) session.getAttribute("playerHand");
                deck.dealFaceUp(playerHand);

                break;
            }

            case "stand": {
                Deck deck = (Deck) session.getAttribute("deck");
                Hand dealerHand = (Hand) session.getAttribute("dealerHand");
                Hand playerHand = (Hand) session.getAttribute("playerHand");

                dealerHand.flipLastCard();
                playerHand.stand();

                while (dealerHand.getHandValue() < 17) {
                    deck.dealFaceUp(dealerHand);
                }

                break;
            }
        }

        response.sendRedirect("/");
    }
}
