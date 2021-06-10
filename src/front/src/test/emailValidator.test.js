import { emailValidator } from '../utils/validators'

it('wrong email validation', () => {

	expect(emailValidator('123ail.ru')).toBe('Неверный формат e-mail');
	expect(emailValidator('123@ail.ru')).toBe(undefined);
	expect(emailValidator('akasha@gmail.com')).toBe(undefined);
	expect(emailValidator('123@gmail.com')).toBe(undefined);
	expect(emailValidator('123@gmailcom')).toBe('Неверный формат e-mail');
	expect(emailValidator('@gmailcom')).toBe('Неверный формат e-mail');
	expect(emailValidator('akasha@.com')).toBe('Неверный формат e-mail');
	expect(emailValidator('akasha@')).toBe('Неверный формат e-mail');
	expect(emailValidator('akasha@.ru')).toBe('Неверный формат e-mail');
	expect(emailValidator('akasha2.ru')).toBe('Неверный формат e-mail');
	expect(emailValidator('akasha@mai.ru')).toBe(undefined);
	expect(emailValidator('akasha@mai123.ru')).toBe(undefined);
	expect(emailValidator('akash_0123_i8udasd{a@mai123.ru')).toBe(undefined);
})