import { Routes } from '@angular/router';
import { KlubyComponent } from './components/kluby/kluby.component';
import { PoslowieComponent } from './components/poslowie/poslowie.component';
import { GlosowaniaComponent } from './components/glosowania/glosowania.component';
import { KlubInfoComponent } from './components/kluby/klub-info/klub-info.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '',
    pathMatch: 'full',
  },
  {
    path: 'kluby',
    component: KlubyComponent,
  },
  {
    path: 'kluby/:id',
    component: KlubInfoComponent,
  },
  {
    path: 'poslowie',
    component: PoslowieComponent,
  },
  {
    path: 'glosowania',
    component: GlosowaniaComponent,
  },
];
