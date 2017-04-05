import { TestBed, inject } from '@angular/core/testing';
import {MyUserService} from "./my-user-service.service";


describe('MyUserService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MyUserService]
    });
  });

  it('should ...', inject([MyUserService], (service: MyUserService) => {
    expect(service).toBeTruthy();
  }));
});
