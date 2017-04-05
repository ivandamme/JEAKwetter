import { TestBed, inject } from '@angular/core/testing';

import {MyLocationService} from "./my-location-service.service";

describe('MyLocationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MyLocationService]
    });
  });

  it('should ...', inject([MyLocationService], (service: MyLocationService) => {
    expect(service).toBeTruthy();
  }));
});
