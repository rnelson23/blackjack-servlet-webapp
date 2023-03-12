<%@ page import="com.example.blackjack.Hand" %>
<%@ page import="com.example.blackjack.Card" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blackjack</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <%! HttpSession session; %>
    <% session = request.getSession(false); %>

    <%! int money = 0; %>
    <%! int bet = 0; %>
    <%! boolean newRound = true; %>

    <%! Hand playerHand; %>
    <%! Hand dealerHand; %>

    <h1>Blackjack</h1>

    <% if (session != null) { %>
        <% money = (int) session.getAttribute("money"); %>
        <% bet = (int) session.getAttribute("bet"); %>
        <% newRound = true; %>

        <% playerHand = (Hand) session.getAttribute("playerHand"); %>
        <% dealerHand = (Hand) session.getAttribute("dealerHand"); %>

        <h2>Dealer: <%= dealerHand.value() %></h2>
        <div class="container">
            <% for (Card card : dealerHand.cards) { %>
                <pre><code><%= card %></code></pre>
            <% } %>
        </div>

        <h2>Player: <%= playerHand.value() %></h2>
        <div class="container">
            <% for (Card card : playerHand.cards) { %>
                <pre><code><%= card %></code></pre>
            <% } %>
        </div>

        <% if (playerHand.isBlackjack() && !dealerHand.isBlackjack()) { %>
            <h2>Blackjack! Player wins!</h2>
            <% money += bet + (bet / 2); %>

        <% } else if (!playerHand.isBlackjack() && dealerHand.isBlackjack()) { %>
            <h2>Blackjack! Dealer wins!</h2>
            <% dealerHand.flip(); %>
            <% money -= bet; %>

        <% } else if (playerHand.isBlackjack() && dealerHand.isBlackjack()) { %>
            <h2>Double Blackjack! Stand-off!</h2>
            <% dealerHand.flip(); %>

        <% } else if (playerHand.value() > 21) { %>
            <h2>Bust! Dealer wins!</h2>
            <% money -= bet; %>

        <% } else if (dealerHand.value() > 21) { %>
            <h2>Bust! Player wins!</h2>
            <% money += bet; %>

        <% } else if (!playerHand.stood) { %>
            <br><form action="${pageContext.request.contextPath}/Controller" method="post">
                <div class="container">
                    <input class="input" type="submit" name="action" value="Hit">
                    <input class="input" type="submit" name="action" value="Stand">
                </div>
            </form>

            <% newRound = false; %>

        <% } else if (playerHand.value() > dealerHand.value()) { %>
            <h2>Player wins!</h2>
            <% money += bet; %>

        <% } else if (playerHand.value() < dealerHand.value()) { %>
            <h2>Dealer wins!</h2>
            <% money -= bet; %>

        <% } else { %>
            <h2>Stand-off!</h2>
        <% } %>

        <% session.setAttribute("money", money); %>
    <% } %>

    <% if (newRound) { %>
        <p>Player's Money: <%= money < 0 ? "-" : "" %>$<%= Math.abs(money) %></p>
        <form action="${pageContext.request.contextPath}/Controller" method="post">
            <label><input type="number" name="bet" placeholder="Bet" min="1"></label>
            <input type="submit" name="action" value="Deal">
        </form>
    <% } %>
</body>
</html>
