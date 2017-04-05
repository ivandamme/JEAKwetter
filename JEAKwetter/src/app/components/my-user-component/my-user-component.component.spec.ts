import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyKwetteraarComponent } from './my-kwetteraar-component.component';

describe('MyKwetteraarComponent', () => {
  let component: MyKwetteraarComponent;
  let fixture: ComponentFixture<MyKwetteraarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyKwetteraarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyKwetteraarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
