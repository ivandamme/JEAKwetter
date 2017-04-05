import { TestBed, inject } from '@angular/core/testing';
import {MyRoleService} from "./my-role-service.service";



describe('MyRoleService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MyRoleService]
    });
  });

  it('should ...', inject([MyRoleService], (service: MyRoleService) => {
    expect(service).toBeTruthy();
  }));
});
