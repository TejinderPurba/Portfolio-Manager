package com.citi.training.portfolio.service;

import com.citi.training.portfolio.entities.*;
import com.citi.training.portfolio.repo.*;
import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateKeyDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private BondRepository bondRepository;
    @Autowired
    private FutureRepository futureRepository;
    @Autowired
    private ExchangeTradedFundRepository exchangeTradedFundRepository;
    @Autowired
    private CashRepository cashRepository;

    public class GraphData {
        public Double value;
        public Object name;

        GraphData(Double value, Object name) {
            this.value = value;
            this.name = name;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        public Object getName() {
            return name;
        }

        public void setName(LocalDate name) {
            this.name = name;
        }
    }

    /**
     * STOCK METHODS
     */
    @Override
    public Collection<Stock> getAllStocks() {
        return stockRepository.findAllSorted();
    }

    @Override
    public Collection<Stock> getAllLatestStocks() {
        return stockRepository.getAllLatestStocks();
    }

    @Override
    public Collection<Stock> getStocksBySymbol(String symbol) {
        return stockRepository.findBySymbol(symbol);
    }

    @Override
    public Collection<Stock> getStocksByName(String name) {
        return stockRepository.findByName(name);
    }

    @Override
    public Collection<Stock> getStocksByType(int type) {
        return stockRepository.findByTransactionType(type);
    }

    @Override
    @Transactional
    public void buyStock(Stock stock) {
        Collection<Stock> recentTransactions = stockRepository.getLatestStockTransactionBySymbol(stock.getSymbol());
        if (recentTransactions.size() > 0) {
            Stock recentTransaction = recentTransactions.iterator().next();
            stock.setTotalQuantity(recentTransaction.getTotalQuantity() + stock.getQuantityAffected());
        } else stock.setTotalQuantity(stock.getQuantityAffected());
        stock.setTotalValue(stock.getTotalQuantity() * stock.getMarketValue());
        stockRepository.save(stock);
    }

    @Override
    @Transactional
    public void sellStock(Stock stock) {
        Collection<Stock> recentTransactions = stockRepository.getLatestStockTransactionBySymbol(stock.getSymbol());
        if (recentTransactions.size() > 0) {
            Stock recentTransaction = recentTransactions.iterator().next();
            if (stock.getQuantityAffected() <= recentTransaction.getTotalQuantity()) {
                stock.setTotalQuantity(recentTransaction.getTotalQuantity() - stock.getQuantityAffected());
                stock.setTotalValue(stock.getTotalQuantity() * stock.getMarketValue());
            }
            stockRepository.save(stock);
        }
    }

    /**
     * EXCHANGED TRADED FUNDS METHODS
     */
    @Override
    public Collection<ExchangeTradedFund> getAllExchangeTradedFunds() {
        return exchangeTradedFundRepository.findAllSorted();
    }

    @Override
    public Collection<ExchangeTradedFund> getAllLatestExchangeTradedFunds() {
        return exchangeTradedFundRepository.getAllLatestExchangeTradedFunds();
    }

    @Override
    public Collection<ExchangeTradedFund> getExchangeTradedFundsBySymbol(String symbol) {
        return exchangeTradedFundRepository.findBySymbol(symbol);
    }

    @Override
    public Collection<ExchangeTradedFund> getExchangeTradedFundsByName(String name) {
        return exchangeTradedFundRepository.findByName(name);
    }

    @Override
    public Collection<ExchangeTradedFund> getExchangeTradedFundsByType(int type) {
        return exchangeTradedFundRepository.findByTransactionType(type);
    }

    @Override
    @Transactional
    public void buyExchangeTradedFund(ExchangeTradedFund exchangeTradedFund) {
        Collection<ExchangeTradedFund> recentTransactions = exchangeTradedFundRepository.getLatestExchangeTradedFundTransactionBySymbol(exchangeTradedFund.getSymbol());
        if (recentTransactions.size() > 0) {
            ExchangeTradedFund recentTransaction = recentTransactions.iterator().next();
            exchangeTradedFund.setTotalQuantity(recentTransaction.getTotalQuantity() + exchangeTradedFund.getQuantityAffected());
        } else exchangeTradedFund.setTotalQuantity(exchangeTradedFund.getQuantityAffected());
        exchangeTradedFund.setTotalValue(exchangeTradedFund.getTotalQuantity() * exchangeTradedFund.getMarketValue());
        exchangeTradedFundRepository.save(exchangeTradedFund);
    }

    @Override
    @Transactional
    public void sellExchangeTradedFund(ExchangeTradedFund exchangeTradedFund) {
        Collection<ExchangeTradedFund> recentTransactions = exchangeTradedFundRepository.getLatestExchangeTradedFundTransactionBySymbol(exchangeTradedFund.getSymbol());
        ;
        if (recentTransactions.size() > 0) {
            ExchangeTradedFund recentTransaction = recentTransactions.iterator().next();
            if (exchangeTradedFund.getQuantityAffected() <= recentTransaction.getTotalQuantity()) {
                exchangeTradedFund.setTotalQuantity(recentTransaction.getTotalQuantity() - exchangeTradedFund.getQuantityAffected());
                exchangeTradedFund.setTotalValue(exchangeTradedFund.getTotalQuantity() * exchangeTradedFund.getMarketValue());
            }
            exchangeTradedFundRepository.save(exchangeTradedFund);
        }
    }

    /**
     * CASH METHODS
     */
    @Override
    public Collection<Cash> getAllCash() {
        return cashRepository.findAllSorted();
    }

    @Override
    public Collection<Cash> getAllLatestCash() {
        return cashRepository.getAllLatestCashAccounts();
    }

    @Override
    public Collection<Cash> getCashByTransactionType(int type) {
        return cashRepository.findByTransactionType(type);
    }

    @Override
    public Collection<Cash> getCashByAccountType(int type) {
        return cashRepository.findByAccountType(type);
    }

    @Override
    public Collection<Cash> getCashByAccountNumber(int account) {
        return cashRepository.findByAccountNumber(account);
    }

    @Override
    public Collection<Cash> getCashByFinancialInstitution(String institution) {
        return cashRepository.findByFinancialInstitution(institution);
    }

    @Override
    @Transactional
    public void depositCash(Cash cash) {
        Collection<Cash> recentTransactions = cashRepository.getLatestCashAccountTransactionByAccountNumber(cash.getAccountNumber());
        if (recentTransactions.size() > 0) {
            Cash recentTransaction = recentTransactions.iterator().next();
            cash.setBalance(recentTransaction.getBalance() + cash.getTransactionAmount());
        } else cash.setBalance(cash.getTransactionAmount());
        cashRepository.save(cash);
    }

    @Override
    @Transactional
    public void withdrawCash(Cash cash) {
        Collection<Cash> recentTransactions = cashRepository.getLatestCashAccountTransactionByAccountNumber(cash.getAccountNumber());
        if (recentTransactions.size() > 0) {
            Cash recentTransaction = recentTransactions.iterator().next();
            if (cash.getTransactionAmount() <= recentTransaction.getBalance()) {
                cash.setBalance(recentTransaction.getBalance() - cash.getTransactionAmount());
                cashRepository.save(cash);
            }
        }
    }

    /**
     * BOND METHODS
     */
    @Override
    public Collection<Bond> getAllBonds() {
        return bondRepository.findAllSorted();
    }

    @Override
    public Collection<Bond> getAllLatestBonds() {
        return bondRepository.getAllLatestBonds();
    }

    @Override
    public Collection<Bond> getBondsByIssuer(String issuer) {
        return bondRepository.findByIssuer(issuer);
    }

    @Override
    public Collection<Bond> getBondsByName(String name) {
        return bondRepository.findByName(name);
    }

    @Override
    public Collection<Bond> getBondsByBondType(String bondType) {
        return bondRepository.findByBondType(bondType);
    }

    @Override
    public Collection<Bond> getBondsByTransactionType(int transactionType) {
        return bondRepository.findByTransactionType(transactionType);
    }

    @Override
    @Transactional
    public void buyBond(Bond bond) { //Assume a bond name is unique
        Collection<Bond> recentTransactions = bondRepository.getLatestBondTransaction(bond.getName());
        if (recentTransactions.size() == 0) {
            bondRepository.save(bond);
        }
    }

    @Override
    @Transactional
    public void sellBond(Bond bond) { //Assume a bond has to be sold as a whole
        Collection<Bond> recentTransactions = bondRepository.getLatestBondTransaction(bond.getName());
        if (recentTransactions.size() > 0) {
            Bond recentTransaction = recentTransactions.iterator().next();
            if (recentTransaction.getTotalValue() > 0) {
                bondRepository.save(bond);
            }
        }
    }

    /**
     * PORTFOLIO METHODS
     */
    @Override
    public double dummyCurrentMarketValue(String symbol) {
        return Math.floor(10 + (Math.random() * 145)) / 100; // Return random value between 10 and 145, truncating to 2 decimal places
    }

    @Override
    public double getInvestmentValue() {
        Collection<Stock> stocksTotal = stockRepository.getAllLatestStocks();
        double investmentValue = 0;
        for (Stock stock : stocksTotal) {
            investmentValue += (stock.getTotalQuantity() * dummyCurrentMarketValue(stock.getSymbol()));
        }

        Collection<ExchangeTradedFund> exchangeTradedFundTotal = exchangeTradedFundRepository.getAllLatestExchangeTradedFunds();
        for (ExchangeTradedFund exchangeTradedFund : exchangeTradedFundTotal) {
            investmentValue += (exchangeTradedFund.getTotalQuantity() * dummyCurrentMarketValue(exchangeTradedFund.getSymbol()));
        }

        Collection<Bond> bondTotal = bondRepository.getAllLatestBonds();
        for (Bond bond : bondTotal) {
            investmentValue += bond.getTotalValue();
        }
        return investmentValue;
    }

    @Override
    public double getCashValue() {
        Collection<Cash> cashTotal = cashRepository.getAllLatestCashAccounts();
        double cashValue = 0;
        for (Cash cash : cashTotal) {
            cashValue += cash.getBalance();
        }
        return cashValue;
    }

    @Override
    public double[] getNetWorth() {
        double[] netWorth = {getInvestmentValue(), getCashValue(), getInvestmentValue() + getCashValue()};
        return netWorth;
    }

    @Override
    public Double dummyCurrentMarketMover(String symbol, int period) {
        double moveAmount = (Math.floor((Math.random() * 5.5) * 100)) / 100; // Return random value between 0 and 5.5%, truncating to 2 decimal places
        if (Math.round(Math.random()) == 1) moveAmount *= -1; // Random chance its either a gain or loss
        return moveAmount;
    }

    @Override
    public SortedMap getMarketMovers(int period) {
        SortedMap<Double, String> marketMovers = new TreeMap<>();

        // Stock Analysis
        Collection<Stock> latestStocks = stockRepository.getAllLatestStocks();
        for (Stock stock : latestStocks) {
            if (stock.getTotalQuantity() > 0) {
                Double currMarketMoverValue = dummyCurrentMarketMover(stock.getSymbol(), period);
                marketMovers.put(currMarketMoverValue, stock.getSymbol());
            }
        }

        // Exchange Traded Fund Analysis
        Collection<ExchangeTradedFund> latestExchangeTradedFunds = exchangeTradedFundRepository.getAllLatestExchangeTradedFunds();
        for (ExchangeTradedFund exchangeTradedFund : latestExchangeTradedFunds) {
            if (exchangeTradedFund.getTotalQuantity() > 0) {
                Double currMarketMoverValue = dummyCurrentMarketMover(exchangeTradedFund.getSymbol(), period);
                marketMovers.put(currMarketMoverValue, exchangeTradedFund.getSymbol());
            }
        }

//        // Bond Analysis - No market movers for bonds
//        Collection<Bond> latestBonds = bondRepository.getAllLatestBonds();
//        for(Bond bond : latestBonds) {
//            Double currMarketMoverValue = dummyCurrentMarketMover(bond.getIssuer());
//            marketMovers.put(currMarketMoverValue, bond.getName());
//        }

        // Remove duplicates
        final Iterator<Map.Entry<Double, String>> iter = marketMovers.entrySet().iterator();
        final HashSet<String> valueSet = new HashSet<String>();
        while (iter.hasNext()) {
            final SortedMap.Entry<Double, String> next = iter.next();
            if (!valueSet.add(next.getValue())) {
                iter.remove();
            }
        }

        return marketMovers;
    }

    @Override
    public double[] getIncomeCashFlow(String date) {
        double[] incomeCashFlow = new double[5];
        Collection<Cash> cashFlows = cashRepository.getLatestIncomeCashFlows(date);
        for (Cash cash : cashFlows) {
            if (cash.getAccountType() == 1) {
                incomeCashFlow[1] += cash.getTransactionAmount();
            } else if (cash.getAccountType() == 2) {
                incomeCashFlow[2] += cash.getTransactionAmount();
            } else if (cash.getAccountType() == 3) {
                incomeCashFlow[3] += cash.getTransactionAmount();
            } else if (cash.getAccountType() == 4) {
                incomeCashFlow[4] += cash.getTransactionAmount();
            }
        }
        incomeCashFlow[0] = incomeCashFlow[1] + incomeCashFlow[2] + incomeCashFlow[3] + incomeCashFlow[4];
        return incomeCashFlow;
    }

    @Override
    public double[] getExpenseCashFlow(String date) {
        double[] expenseCashFlow = new double[5];
        Collection<Cash> cashFlows = cashRepository.getLatestExpenseCashFlows(date);
        for (Cash cash : cashFlows) {
            if (cash.getAccountType() == 1) {
                expenseCashFlow[1] += cash.getTransactionAmount();
            } else if (cash.getAccountType() == 2) {
                expenseCashFlow[2] += cash.getTransactionAmount();
            } else if (cash.getAccountType() == 3) {
                expenseCashFlow[3] += cash.getTransactionAmount();
            } else if (cash.getAccountType() == 4) {
                expenseCashFlow[4] += cash.getTransactionAmount();
            }
        }
        expenseCashFlow[0] = expenseCashFlow[1] + expenseCashFlow[2] + expenseCashFlow[3] + expenseCashFlow[4];
        return expenseCashFlow;
    }

    @Override
    public double getCashFlow(String date) {
        double cashFlow = getIncomeCashFlow(date)[0] - getExpenseCashFlow(date)[0];
        return cashFlow;
    }

    @Override
    public SortedMap<LocalDate, Double> getCashHistory() {
        SortedMap<LocalDate, Double> cashHistory = new TreeMap<>();
        Collection<Cash> allCash = cashRepository.findAllSorted();
        Map<Integer, Double> cashValues = new HashMap<>();

        if (allCash.size() > 0) {
            Cash firstCash = allCash.iterator().next();
            LocalDate currDate = firstCash.getDateTime().toLocalDate();
            LocalDate tomorrow = LocalDate.now().plusDays(1);
            Double currWorth = 0.0;

            do {
                currWorth = 0.0;
                Collection<Cash> currCash = cashRepository.getAllLatestCashAccountsByDate(currDate.toString());
                if (currCash.size() > 0) {
                    for (Cash cash : currCash) {
                        cashValues.put(cash.getAccountNumber(), cash.getBalance());
                    }
                }
                for (var cash : cashValues.entrySet()) {
                    currWorth += cash.getValue();
                }
                cashHistory.put(currDate, currWorth);
                currDate = currDate.plusDays(1);
            } while (!currDate.equals(tomorrow));
        }

        return cashHistory;
    }

    @Override
    public SortedMap<LocalDate, Double> getStockHistory() {
        SortedMap<LocalDate, Double> stockHistory = new TreeMap<>();
        Collection<Stock> allStocks = stockRepository.findAllSorted();
        Map<String, Double> stockValues = new HashMap<>();

        if (allStocks.size() > 0) {
            Stock firstStock = allStocks.iterator().next();
            LocalDate currDate = firstStock.getDateTime().toLocalDate();
            LocalDate tomorrow = LocalDate.now().plusDays(1);
            Double currWorth = 0.0;

            do {
                currWorth = 0.0;
                Collection<Stock> currStocks = stockRepository.getAllLatestStocksByDate(currDate.toString());
                if (currStocks.size() > 0) {
                    for (Stock stock : currStocks) {
                        stockValues.put(stock.getSymbol(), stock.getTotalValue());
                    }
                }
                for (var stock : stockValues.entrySet()) {
                    currWorth += stock.getValue();
                }
                stockHistory.put(currDate, currWorth);
                currDate = currDate.plusDays(1);
            } while (!currDate.equals(tomorrow));
        }

        return stockHistory;
    }

    @Override
    public SortedMap<LocalDate, Double> getExchangeTradedFundHistory() {
        SortedMap<LocalDate, Double> exchangeTradedFundHistory = new TreeMap<>();
        Collection<ExchangeTradedFund> allExchangeTradedFunds = exchangeTradedFundRepository.findAllSorted();
        Map<String, Double> exchangeTradedFundValues = new HashMap<>();

        if (allExchangeTradedFunds.size() > 0) {
            ExchangeTradedFund firstExchangeTradedFund = allExchangeTradedFunds.iterator().next();
            LocalDate currDate = firstExchangeTradedFund.getDateTime().toLocalDate();
            LocalDate tomorrow = LocalDate.now().plusDays(1);
            Double currWorth = 0.0;

            do {
                currWorth = 0.0;
                Collection<ExchangeTradedFund> currExchangeTradedFunds = exchangeTradedFundRepository.getAllLatestExchangeTradedFundsByDate(currDate.toString());
                if (currExchangeTradedFunds.size() > 0) {
                    for (ExchangeTradedFund exchangeTradedFund : currExchangeTradedFunds) {
                        exchangeTradedFundValues.put(exchangeTradedFund.getSymbol(), exchangeTradedFund.getTotalValue());
                    }
                }
                for (var exchangeTradedFund : exchangeTradedFundValues.entrySet()) {
                    currWorth += exchangeTradedFund.getValue();
                }
                exchangeTradedFundHistory.put(currDate, currWorth);
                currDate = currDate.plusDays(1);
            } while (!currDate.equals(tomorrow));
        }

        return exchangeTradedFundHistory;
    }

    public SortedMap<LocalDate, Double> getBondHistory() {
        SortedMap<LocalDate, Double> bondHistory = new TreeMap<>();
        Collection<Bond> allBonds = bondRepository.findAllSorted();
        Map<String, Double> bondValues = new HashMap<>();

        if (allBonds.size() > 0) {
            Bond firstBond = allBonds.iterator().next();
            LocalDate currDate = firstBond.getDateTime().toLocalDate();
            LocalDate tomorrow = LocalDate.now().plusDays(1);
            Double currWorth = 0.0;

            do {
                currWorth = 0.0;
                Collection<Bond> currBonds = bondRepository.getAllLatestBondsByDate(currDate.toString());
                if (currBonds.size() > 0) {
                    for (Bond bond : currBonds) {
                        bondValues.put(bond.getName(), bond.getTotalValue());
                    }
                }
                for (var bond : bondValues.entrySet()) {
                    currWorth += bond.getValue();
                }
                bondHistory.put(currDate, currWorth);
                currDate = currDate.plusDays(1);
            } while (!currDate.equals(tomorrow));
        }

        return bondHistory;
    }

    @Override
    public LinkedList<GraphData> getNetWorthHistory() {
        SortedMap<LocalDate, Double> networthHistory = new TreeMap<>();
        SortedMap<LocalDate, Double> bondHistory = getBondHistory();
        SortedMap<LocalDate, Double> stockHistory = getStockHistory();
        SortedMap<LocalDate, Double> cashHistory = getCashHistory();
        SortedMap<LocalDate, Double> etfHistory = getExchangeTradedFundHistory();
        LinkedList<GraphData> history = new LinkedList<>();
        for (LocalDate date : bondHistory.keySet()) {
            networthHistory.put(date, bondHistory.get(date));
        }
        for (LocalDate date : stockHistory.keySet()) {
            if (networthHistory.containsKey(date)) {
                networthHistory.put(date, networthHistory.get(date) + stockHistory.get(date));
            } else {
                networthHistory.put(date, stockHistory.get(date));
            }
        }
        for (LocalDate date : etfHistory.keySet()) {
            if (networthHistory.containsKey(date)) {
                networthHistory.put(date, networthHistory.get(date) + etfHistory.get(date));
            } else {
                networthHistory.put(date, etfHistory.get(date));
            }
        }
        for (LocalDate date : cashHistory.keySet()) {
            if (networthHistory.containsKey(date)) {
                networthHistory.put(date, networthHistory.get(date) + cashHistory.get(date));
            } else {
                networthHistory.put(date, cashHistory.get(date));
            }
        }

        for (var entry : networthHistory.entrySet()) {
            GraphData networth = new GraphData(entry.getValue(), entry.getKey());
            history.add(networth);
        }

        return history;

    }
}
