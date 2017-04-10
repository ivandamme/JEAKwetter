import {NgModule}             from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MyUserComponent} from "app/components/my-user-component/my-user-component.component";
import {MyLogInComponent} from "app/components/my-login-component/my-login-component.component";
import {MyProfileComponent} from "app/components/my-profile-component/my-profile-component.component";
import {AuthGuard} from "app/guards/authentication.guard";
import {MyLogOutComponent} from "app/components/my-logout-component/my-logout-component.component";
import {OtherProfileComponent} from "app/components/other-profile-component/other-profile-component.component";
import {UpdateProfileComponent} from "app/components/update-profile-component/update-profile-component.component";
import {MyStartComponent} from "app/components/my-start-component/my-start-component.component";
import {MyKweetComponent} from "../../components/my-kweet-component/my-kweet-component.component";
import {MyHomeComponent} from "../../components/my-home-component/homecomponent";

const routes: Routes = [
  {path: 'user', component: MyUserComponent},
  {path: 'home', component: MyHomeComponent},
  {path: 'kweet', component: MyKweetComponent},
  {path: 'otherprofile', component: OtherProfileComponent},
  {path: 'profile', component: MyProfileComponent, canActivate: [AuthGuard]},
  {path: 'login', component: MyLogInComponent},
  {path: 'logout', component: MyLogOutComponent},
  {path: 'updateprofile', component: UpdateProfileComponent, canActivate: [AuthGuard]},
  {path: 'start', component: MyStartComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
