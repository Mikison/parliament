import { Routes } from '@angular/router';
import { KlubyComponent } from './components/kluby/kluby.component';
import { PoslowieComponent } from './components/poslowie/poslowie.component';
import { GlosowaniaComponent } from './components/glosowania/glosowania.component';
import { KlubInfoComponent } from './components/kluby/klub-info/klub-info.component';
import { PoslowieInfoComponent } from './components/poslowie/poslowie-info/poslowie-info.component';
import { NotFoundPageComponent } from './components/not-found-page/not-found-page.component';
import { GlosowaniaInfoComponent } from './components/glosowania/glosowania-info/glosowania-info.component';

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
    path: 'poslowie/:id',
    component: PoslowieInfoComponent,
  },
  {
    path: 'glosowania',
    component: GlosowaniaComponent,
  },
  {
    path: 'glosowania/:id',
    component: GlosowaniaInfoComponent,
  },
  { path: '404', component: NotFoundPageComponent },
  { path: '**', redirectTo: '/404' },
];
