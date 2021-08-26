import { Component, OnInit } from '@angular/core';
import {PortfolioService} from "../../services/portfolio.service";

@Component({
  selector: 'app-market-movers',
  templateUrl: './market-movers.component.html',
  styleUrls: ['./market-movers.component.css']
})
export class MarketMoversComponent implements OnInit {

  constructor(private portfolioService:PortfolioService) { }
  // we can declare data models for use within this component
  periodParamObj = 1
  response = {}
  
  ngOnInit(): void {
    this.makeMarketMoversByPeriodCall()
  }

  makeMarketMoversCall() {
    // We call the service method by subscribing to the service call
    // Remember the api call with be async so subscribing responds when it returns
    this.portfolioService.getMarketMoversData()
      .subscribe( (data:any)=>{ // any should be a class instead specifying what properties data would have
        this.response = data
        console.log(this.response)
      } )
  }

  makeMarketMoversByPeriodCall() {
    this.portfolioService.getMarketMoversByPeriod(this.periodParamObj)
      .subscribe( (data:any)=>{ // any should be a class instead specifying what properties data would have
        this.response = data
        console.log(this.response)
      } )
  }
}
