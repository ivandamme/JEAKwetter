import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyLogInComponent } from './my-login-component.component';

describe('MyInloggenComponent', () => {
	let component: MyLogInComponent;
	let fixture: ComponentFixture<MyLogInComponent>;

	beforeEach(async(() => {
		TestBed.configureTestingModule({
			declarations: [MyLogInComponent]
		})
			.compileComponents();
	}));

	beforeEach(() => {
		fixture = TestBed.createComponent(MyLogInComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
