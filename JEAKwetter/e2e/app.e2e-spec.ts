import { JEAKwetterPage } from './app.po';

describe('jeakwetter App', () => {
  let page: JEAKwetterPage;

  beforeEach(() => {
    page = new JEAKwetterPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
