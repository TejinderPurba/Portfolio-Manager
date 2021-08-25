import { Injectable } from '@angular/core';
// I wanted to use http in this service
import { HttpClient } from '@angular/common/http'



@Injectable({
  providedIn: 'root'
})
export class PortfolioService {

  constructor(private http:HttpClient) { }

  // We need a method of this service - in this case we call an API end-point
  getStockSummaryData(){ // All httpClients are observables
    return this.http.get('http://localhost:8080/portfolio/lateststocks')
    
  }

  getStockDataBySymbol(params={symbol:''}){ 
    return this.http.get(`http://portfolio-manager-portfolio-manager.namdevops25.conygre.com/portfolio/stock/symbol/${params.symbol}`)
  }


  getNetWorth(){ // All httpClients are observables
    return this.http.get('http://localhost:8080/portfolio/networth')
    
  }

  getTotalCashFlow(date : any){ // All httpClients are observables
    return this.http.get(`http://localhost:8080/portfolio/cashflow/${date}`)
    
  }

  getTotalIncome(date : any){ // All httpClients are observables
    return this.http.get(`http://localhost:8080/portfolio/cashincomeflow/${date}`) 
  }

  getTotalExpense(date : any){ // All httpClients are observables
    return this.http.get(`http://localhost:8080/portfolio/cashexpenseflow/${date}`) 
  }
}
