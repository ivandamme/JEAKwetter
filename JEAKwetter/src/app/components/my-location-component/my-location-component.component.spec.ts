import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyLocatieComponent } from './my-locatie-component.component';

describe('MyLocatieComponent', () => {
  let component: MyLocatieComponent;
  let fixture: ComponentFixture<MyLocatieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyLocatieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyLocatieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
