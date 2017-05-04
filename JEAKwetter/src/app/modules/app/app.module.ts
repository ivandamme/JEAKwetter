import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from "../../components/my-app-component/app.component";
import {AppRoutingModule} from "../app-routing/app-routing.module";
import {MyUserComponent} from "../../components/my-user-component/my-user-component.component";
import {MyLogInComponent} from "../../components/my-login-component/my-login-component.component";
import {MyProfileComponent} from "../../components/my-profile-component/my-profile-component.component";
import {MyLogOutComponent} from "../../components/my-logout-component/my-logout-component.component";
import {OtherProfileComponent} from "../../components/other-profile-component/other-profile-component.component";
import {UpdateProfileComponent} from "../../components/update-profile-component/update-profile-component.component";
import {MyUserService} from "../../services/my-user-service/my-user-service.service";
import {MyKweetService} from "../../services/my-kweet-service/my-kweet-service.service";
import {MyLocationService} from "../../services/my-location-service/my-location-service.service";
import {MyRoleService} from "../../services/my-role-service/my-role-service.service";
import {AuthGuard} from "../../guards/authentication.guard";
import { MyStartComponent } from "app/components/my-start-component/my-start-component.component";
import {MyKweetComponent} from "../../components/my-kweet-component/my-kweet-component.component";
import {MyHomeComponent} from "../../components/my-home-component/homecomponent";
import {MyWebsocketComponent} from "../../components/my-websocket-component/my-websocket-component";

@NgModule({
  declarations: [
    AppComponent,
    MyUserComponent,
    MyKweetComponent,
    MyHomeComponent,
    MyLogInComponent,
    MyProfileComponent,
    OtherProfileComponent,
    UpdateProfileComponent,
    MyLogOutComponent,
    MyStartComponent,
    MyWebsocketComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [
    MyUserService,
    MyKweetService,
    MyLocationService,
    MyRoleService,
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
