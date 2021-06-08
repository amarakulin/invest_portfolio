import { emailValidator } from '../utils/validators'

it('wrong email validation', () => {

	expect(emailValidator('123ail.ru')).toBe(false);
	expect(emailValidator('123@ail.ru')).toBe(true);
	expect(emailValidator('akasha@gmail.com')).toBe(true);
	expect(emailValidator('123@gmail.com')).toBe(true);
	expect(emailValidator('123@gmailcom')).toBe(false);
	expect(emailValidator('@gmailcom')).toBe(false);
	expect(emailValidator('akasha@.com')).toBe(false);
	expect(emailValidator('akasha@')).toBe(false);
	expect(emailValidator('akasha@.ru')).toBe(false);
	expect(emailValidator('akasha2.ru')).toBe(false);
	expect(emailValidator('akasha@mai.ru')).toBe(true);
	expect(emailValidator('akasha@mai123.ru')).toBe(true);
	expect(emailValidator('akash_0123_i8udasd{a@mai123.ru')).toBe(true);
})