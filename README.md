AI-Powered Note-Taking API

A robust, secure, and scalable RESTful API built with Java and Spring Boot for managing personal notes. This backend service features stateless JWT authentication, secure database hosting, and an integrated AI feature that leverages the Groq API to automatically summarize long-form notes.
🚀 Live API Documentation (Swagger UI):https://noteapi-9hpy.onrender.com/swagger-ui/index.html

🛠️ Tech Stack & Technologies
•	Backend Framework: Java 21, Spring Boot 3
•	Security: Spring Security, JWT (JSON Web Tokens)
•	Database: MySQL, Spring Data JPA, Hibernate
•	AI Integration: Groq API (External LLM)
•	Cloud Hosting: Render (Web Service) & Aiven (Managed MySQL)
•	API Documentation: Springdoc OpenAPI (Swagger)

✨ Key Features
•	User Authentication: Secure registration and login endpoints using stateless JWT token validation.
•	CRUD Operations: Create, Read, Update, and Delete personalized notes. Users can only access their own notes.
•	AI Summarization: Built-in endpoint that sends note content to the Groq API to return a concise, AI-generated summary.
•	Cloud Deployed: Fully deployed infrastructure with enforced SSL database connections and UTC-standardized timestamps.
•	Interactive Documentation: Fully configured Swagger UI for easy endpoint testing directly from the browser.

🚨 How to Test the Live API
This is strictly a backend architecture project. To interact with the live endpoints, please use the provided Swagger UI link:
	1.	Navigate to the Swagger UI.
	2.	Execute the POST /register endpoint with a test username and password to create an account.
	3.	Execute the POST /login endpoint with those credentials to receive your JWT token.
	4.	Click the green Authorize button at the top of the Swagger page and paste your token (prefix with Bearer  if required by your setup).
	5.	You can now securely test the Note CRUD operations and the AI Summarize feature!
