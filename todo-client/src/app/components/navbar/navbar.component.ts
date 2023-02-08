import { ThisReceiver } from '@angular/compiler';
import { Component, Input } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable, map } from 'rxjs';
import { UserAndToken } from 'src/app/models/user-and-token.model';
import { deleteuserSession } from 'src/app/state/action/user-session.action';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  @Input()
  title: string = "";

  session$: Observable<UserAndToken>

  constructor(
    private store: Store<{session: UserAndToken}>
  ){
    this.session$ = this.store.select('session');
  }

  logout(){
    this.store.dispatch(deleteuserSession());
  }
}
