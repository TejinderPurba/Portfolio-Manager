import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MarketMoversComponent } from './market-movers/market-movers.component';
import { NetWorthComponent } from './net-worth/net-worth.component';
import { SummaryComponent } from './summary/summary.component';
import { Investment2Component } from './investment2/investment2.component';
import {MainComponent} from './main.component'

// All routes are inspected in strict order until a match is found
const routes: Routes = [
  {path:'', redirectTo: '/home', pathMatch:'full'},
  {path:'home', component:MainComponent},
  {path:'market-movers', component:MarketMoversComponent},
  {path:'net-worth', component:NetWorthComponent},
  {path:'networth', component:NetWorthComponent},
  {path:'summary', component:SummaryComponent},
  {path:'mynetworth', component:Investment2Component},
  {path:'**', redirectTo: '/home', pathMatch:'full'} // put this in only when the other routes work!
];

@NgModule({
  imports: [RouterModule.forRoot(routes),],
  exports: [RouterModule]
})
export class AppRoutingModule { }
