package com.citi.training.portfolio.repo;


import com.citi.training.portfolio.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    /**
     * Locates all stocks sorted by date ascending.
     * @return Collection of all stocks sorted by date time ascending.
     */
    @Query(
            value = "SELECT * from stocks order by date_time asc",
            nativeQuery = true)
    Collection<Stock> findAllSorted();

    /**
     * Locates a stock based on symbol.
     * @param symbol the stock symbol to be used in the lookup.
     * @return Collection of stocks that match the symbol provided.
     */
    Collection<Stock> findBySymbol(String symbol);

    /**
     * Locates a stock based on name.
     * @param name the stock name to be used in the lookup.
     * @return Collection of stocks that match the name provided.
     */
    Collection<Stock> findByName(String name);

    /**
     * Locates a stock based on type.
     * @param type the stock type to be used in the lookup.
     * @return Collection of stocks that match the type provided.
     */
    Collection<Stock> findByTransactionType(int type);

    /**
     * Retrieves the latest transaction of each stock.
     * @return Collection of the latest transaction for each stock.
     */
    @Query(
            value = "SELECT * from stocks t1 where date_time = (select max(date_time) from stocks where t1.symbol = stocks.symbol) order by date_time desc",
            nativeQuery = true)
    Collection<Stock> getAllLatestStocks();

    /**
     * Retrieves the latest transaction of each stock by date.
     * @param date the date to be fetched.
     * @return Collection of the latest transaction for each stock by date.
     */
    @Query(
            value = "SELECT * from stocks t1 where date_time = (select max(date_time) from stocks where t1.symbol = stocks.symbol and DATE(date_time) = :date) order by date_time desc",
            nativeQuery = true)
    Collection<Stock> getAllLatestStocksByDate(@Param("date") String date);

    /**
     * Retrieves the latest transaction of a specific stock.
     * @param symbol the stock symbol to be fetched.
     * @return The latest transaction for the specified stock.
     */
    @Query(
            value = "SELECT * from stocks t1 where date_time = (select max(date_time) from stocks where stocks.symbol = :symbol) order by date_time desc",
            nativeQuery = true)
    Collection<Stock> getLatestStockTransactionBySymbol(@Param("symbol") String symbol);
}